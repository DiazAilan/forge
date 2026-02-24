package forge.gui.menu;

import javax.swing.*;
import java.util.List;
import net.miginfocom.swing.MigLayout;

public class JumpstartMenu extends JPanel {
    private final JComboBox<String> deck1Selector;
    private final JComboBox<String> deck2Selector;
    private final JButton mergeButton;
    private final JLabel statusLabel;

    public JumpstartMenu(List<String> availableDecks) {
        setLayout(new MigLayout("wrap 2, insets 20, gapx 20, gapy 10", "[right][grow,fill]"));

        add(new JLabel("Choose Deck 1:"), "span, split 2");
        deck1Selector = new JComboBox<>(availableDecks.toArray(new String[0]));
        add(deck1Selector, "growx, wrap");

        add(new JLabel("Choose Deck 2:"), "span, split 2");
        deck2Selector = new JComboBox<>(availableDecks.toArray(new String[0]));
        add(deck2Selector, "growx, wrap");

        mergeButton = new JButton("Merge Decks and Play");
        add(mergeButton, "span, growx, gaptop 15");

        statusLabel = new JLabel("Select two decks to play Jumpstart.");
        add(statusLabel, "span, growx, gaptop 10");

        mergeButton.addActionListener(e -> {
            String deck1 = (String) deck1Selector.getSelectedItem();
            String deck2 = (String) deck2Selector.getSelectedItem();
            if (deck1.equals(deck2)) {
                statusLabel.setText("Please select two different decks.");
                return;
            }
            // TODO: Load and merge decks, then start game
            statusLabel.setText("Decks " + deck1 + " and " + deck2 + " merged. Ready to play!");
        });
    }
}
