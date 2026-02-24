
package forge.screens.home;

import forge.gamemodes.match.HostedMatch;
import forge.game.player.RegisteredPlayer;
import forge.player.LobbyPlayerHuman;
import forge.ai.LobbyPlayerAi;
import forge.deck.Deck;
import forge.deck.DeckSection;
import forge.gui.GuiBase;

import java.util.Map;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import forge.itemmanager.DeckManager;
import forge.deck.DeckProxy;
import forge.game.GameType;
import forge.screens.deckeditor.CDeckEditorUI;

public class JumpstartLobby extends JPanel {
    // For simplicity, only 2 players (can be extended)
    private final JumpstartPlayerPanel player1Panel;
    private final JumpstartPlayerPanel player2Panel;
    private final JButton btnStart;

    public JumpstartLobby() {
        setLayout(new MigLayout("insets 20, gapx 20, gapy 10, wrap 1", "[grow,fill]"));
        // Use DeckManager to get Jumpstart packs (decks)
        DeckManager deckManager = new DeckManager(GameType.Constructed, CDeckEditorUI.SINGLETON_INSTANCE.getCDetailPicture());
        deckManager.setPool(DeckProxy.getAllConstructedDecks()); // Use all constructed decks for now

        player1Panel = new JumpstartPlayerPanel("Player 1", deckManager);
        player2Panel = new JumpstartPlayerPanel("Player 2", deckManager);
        btnStart = new JButton("Start");
        add(player1Panel, "growx, wrap");
        add(player2Panel, "growx, wrap");
        add(btnStart, "center, gaptop 20");

        btnStart.addActionListener(e -> {
            // Get selected packs for each player
            DeckProxy p1Pack1 = player1Panel.getPack1();
            DeckProxy p1Pack2 = player1Panel.getPack2();
            DeckProxy p2Pack1 = player2Panel.getPack1();
            DeckProxy p2Pack2 = player2Panel.getPack2();

            // Merge decks for each player
            Deck deck1 = new Deck("Jumpstart P1");
            if (p1Pack1 != null) deck1.getOrCreate(DeckSection.Main).addAll(p1Pack1.getDeck().getMain());
            if (p1Pack2 != null) deck1.getOrCreate(DeckSection.Main).addAll(p1Pack2.getDeck().getMain());

            Deck deck2 = new Deck("Jumpstart P2");
            if (p2Pack1 != null) deck2.getOrCreate(DeckSection.Main).addAll(p2Pack1.getDeck().getMain());
            if (p2Pack2 != null) deck2.getOrCreate(DeckSection.Main).addAll(p2Pack2.getDeck().getMain());


            RegisteredPlayer player1 = new RegisteredPlayer(deck1);
            if (player1Panel.isAI()) {
                player1.setPlayer(new LobbyPlayerAi(player1Panel.getPlayerName(), null));
            } else {
                player1.setPlayer(new LobbyPlayerHuman(player1Panel.getPlayerName()));
            }

            RegisteredPlayer player2 = new RegisteredPlayer(deck2);
            if (player2Panel.isAI()) {
                player2.setPlayer(new LobbyPlayerAi(player2Panel.getPlayerName(), null));
            } else {
                player2.setPlayer(new LobbyPlayerHuman(player2Panel.getPlayerName()));
            }

            // Start the match
            HostedMatch hostedMatch = GuiBase.getInterface().hostMatch();
            // Set the main human player for UI focus, or null if both are AI
            RegisteredPlayer mainHuman = null;
            if (!player1Panel.isAI()) {
                mainHuman = player1;
            } else if (!player2Panel.isAI()) {
                mainHuman = player2;
            }
            hostedMatch.startMatch(
                GameType.Constructed, // Use GameType.Jumpstart if available
                null,
                java.util.Arrays.asList(player1, player2),
                mainHuman,
                GuiBase.getInterface().getNewGuiGame()
            );
        });
    }

    public JumpstartPlayerPanel getPlayer1Panel() { return player1Panel; }
    public JumpstartPlayerPanel getPlayer2Panel() { return player2Panel; }
    public JButton getBtnStart() { return btnStart; }

    // Add logic for merging packs and starting game as needed
}

class JumpstartPlayerPanel extends JPanel {
    private final JTextField txtName;
    private final JComboBox<DeckProxy> pack1Selector;
    private final JComboBox<DeckProxy> pack2Selector;
    private final JCheckBox chkAI;

    public JumpstartPlayerPanel(String defaultName, DeckManager deckManager) {
        setLayout(new MigLayout("insets 10, gapx 10, gapy 5, wrap 2", "[right][grow,fill]"));
        add(new JLabel("Name:"));
        txtName = new JTextField(defaultName);
        add(txtName, "growx, wrap");
        add(new JLabel("Pack 1:"));
        pack1Selector = new JComboBox<>();
        add(pack1Selector, "growx, wrap");
        add(new JLabel("Pack 2:"));
        pack2Selector = new JComboBox<>();
        add(pack2Selector, "growx, wrap");
        add(new JLabel("AI Player:"));
        chkAI = new JCheckBox();
        add(chkAI, "wrap");

        // Populate selectors with DeckProxy objects from DeckManager
        for (Map.Entry<DeckProxy, Integer> entry : deckManager.getPool()) {
            DeckProxy deck = entry.getKey();
            pack1Selector.addItem(deck);
            pack2Selector.addItem(deck);
        }
        pack1Selector.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof DeckProxy) {
                    setText(((DeckProxy) value).getName());
                }
                return this;
            }
        });
        pack2Selector.setRenderer(pack1Selector.getRenderer());
    }

    public String getPlayerName() { return txtName.getText(); }
    public DeckProxy getPack1() { return (DeckProxy) pack1Selector.getSelectedItem(); }
    public DeckProxy getPack2() { return (DeckProxy) pack2Selector.getSelectedItem(); }
    public boolean isAI() { return chkAI.isSelected(); }
}
