package forge.deck;

// ...existing code...
import forge.item.PaperCard;
import java.util.ArrayList;
import java.util.List;

public class JumpstartDeckMerger {
    /**
     * Merges two decks (lists of PaperCard) into a single deck for Jumpstart.
     * @param deck1 First deck (20 cards)
     * @param deck2 Second deck (20 cards)
     * @return Merged deck (40 cards)
     */
    public static List<PaperCard> merge(List<PaperCard> deck1, List<PaperCard> deck2) {
        List<PaperCard> merged = new ArrayList<>();
        if (deck1 != null) merged.addAll(deck1);
        if (deck2 != null) merged.addAll(deck2);
        return merged;
    }

    /**
     * Optionally, create a Deck object from merged cards.
     */
    public static Deck createJumpstartDeck(List<PaperCard> mergedCards) {
        Deck deck = new Deck();
        for (PaperCard card : mergedCards) {
            deck.getMain().add(card);
        }
        return deck;
    }
}
