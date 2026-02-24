package forge.deck;

// ...existing code...
import forge.item.PaperCard;
import java.util.List;

public class JumpstartDeckEditor {
    // Forces legality to at least 20 cards
    public static boolean isLegalJumpstartDeck(List<PaperCard> deck) {
        return deck != null && deck.size() >= 20;
    }

    // Additional editor logic can be implemented here
}
