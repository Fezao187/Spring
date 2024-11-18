package kristine.card_generator.models.responses;

import kristine.card_generator.models.entities.MyCard;

public class MyCardResponse {
    private MyCard myCard;
    public MyCardResponse(MyCard myCard) {
        this.myCard = myCard;
    }

    public MyCard getMyCard() {
        return myCard;
    }

    public void setMyCard(MyCard myCard) {
        this.myCard = myCard;
    }
}
