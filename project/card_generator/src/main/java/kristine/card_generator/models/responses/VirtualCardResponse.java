package kristine.card_generator.models.responses;

import kristine.card_generator.models.entities.VirtualCard;

public class VirtualCardResponse {
    private VirtualCard virtualCard;
    private String response;

    public VirtualCardResponse(VirtualCard virtualCard) {
        this.virtualCard = virtualCard;
    }
    public VirtualCardResponse(String response) {
        this.response = response;
    }
    public VirtualCardResponse(VirtualCard virtualCard, String response) {
        this.virtualCard = virtualCard;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public VirtualCard getVirtualCard() {
        return virtualCard;
    }

    public void setVirtualCard(VirtualCard virtualCard) {
        this.virtualCard = virtualCard;
    }
}
