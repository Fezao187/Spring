package kristine.card_generator.models;

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
