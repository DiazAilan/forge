
package forge.screens.home.sanctioned;

import forge.screens.home.IVSubmenu;
import forge.screens.home.EMenuGroup;
import forge.screens.home.JumpstartLobby;
import forge.gui.framework.EDocID;
import forge.screens.home.VHomeUI;
import net.miginfocom.swing.MigLayout;
import forge.gui.framework.DragCell;
import forge.gui.framework.DragTab;
import forge.screens.home.LblHeader;
import forge.screens.home.StartButton;
import forge.util.Localizer;
import javax.swing.JPanel;
public enum VSubmenuJumpstart implements IVSubmenu<CSubmenuJumpstart> {
    SINGLETON_INSTANCE;

    private final Localizer localizer = Localizer.getInstance();
    // Fields used with interface IVDoc
    private DragCell parentCell;
    private final DragTab tab = new DragTab(localizer.getMessage("lblJumpstart"));
    private final LblHeader lblTitle = new LblHeader(localizer.getMessage("lblJumpstart"));
    private final JumpstartLobby lobbyPanel = new JumpstartLobby();
    private final StartButton btnStart = new StartButton();

    VSubmenuJumpstart() {
        // Any additional setup if needed
    }


    //========== Overridden from IVDoc
    @Override
    public EDocID getDocumentID() {
        return EDocID.HOME_JUMPSTART;
    }

    @Override
    public DragTab getTabLabel() {
        return tab;
    }

    @Override
    public CSubmenuJumpstart getLayoutControl() {
        return CSubmenuJumpstart.SINGLETON_INSTANCE;
    }

    @Override
    public void setParentCell(DragCell cell0) {
        this.parentCell = cell0;
    }

    @Override
    public DragCell getParentCell() {
        return parentCell;
    }

    //========== Overridden from IVSubmenu
    @Override
    public EMenuGroup getGroupEnum() {
        return EMenuGroup.SANCTIONED;
    }

    @Override
    public String getMenuTitle() {
        return localizer.getMessage("lblJumpstart");
    }

    @Override
    public EDocID getItemEnum() {
        return EDocID.HOME_JUMPSTART;
    }

    @Override
    public void populate() {
        JPanel container = VHomeUI.SINGLETON_INSTANCE.getPnlDisplay();
        container.removeAll();
        container.setLayout(new MigLayout("insets 0, gap 0, wrap, ax right"));

        lblTitle.setBackground(forge.toolbox.FSkin.getColor(forge.toolbox.FSkin.Colors.CLR_THEME2));
        container.add(lblTitle, "w 80%!, h 40px!, gap 0 0 15px 15px, ax right");

        // Add JumpstartMenu panel (deck selectors, merge button, status)
        container.add(lobbyPanel, "w 80%!, gap 0 10% 0 0, pushy, growy");

        container.revalidate();
        container.repaint();
    }
}