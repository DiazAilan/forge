package forge.screens.home.sanctioned;

import forge.gui.framework.ICDoc;
// JumpstartMenu import removed

/**
 * Controls the Jumpstart submenu in the home UI.
 *
 * <br><br><i>(C at beginning of class name denotes a control class.)</i>
 */
public enum CSubmenuJumpstart implements ICDoc {
    SINGLETON_INSTANCE;

    private final VSubmenuJumpstart view = VSubmenuJumpstart.SINGLETON_INSTANCE;

    @Override
    public void register() {
        // Register menu actions if needed
    }

    @Override
    public void update() {
        // Update logic if needed
    }

    @Override
    public void initialize() {
        // Initialization logic if needed
    }



    public VSubmenuJumpstart getView() {
        return view;
    }
}