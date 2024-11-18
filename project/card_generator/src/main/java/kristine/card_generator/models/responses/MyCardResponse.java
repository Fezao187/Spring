package kristine.card_generator.models.responses;

import kristine.card_generator.models.entities.MyCard;

public class MyCardResponse {
    private MyCard myCard;
    private String response;

    public MyCard getMyCard() {
        return myCard;
    }

    public MyCardResponse(MyCard myCard, String response) {
        this.myCard = myCard;
        this.response = response;
    }

    public MyCardResponse(MyCard myCard) {
        this.myCard = myCard;
    }

    public MyCardResponse(String response) {
        this.response = response;
    }

    public void setMyCard(MyCard myCard) {
        this.myCard = myCard;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
