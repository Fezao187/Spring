package kristine.card_generator.models.responses;

import kristine.card_generator.models.entities.VirtualCard;

public class VirtualCardResponse {
    private VirtualCard virtualCard;
    public VirtualCardResponse(VirtualCard virtualCard) {
        this.virtualCard = virtualCard;
    }

    public VirtualCard getVirtualCard() {
        return virtualCard;
    }

    public void setVirtualCard(VirtualCard virtualCard) {
        this.virtualCard = virtualCard;
    }
}
