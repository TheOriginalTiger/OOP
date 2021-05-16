public class Order {
    final int id;
    final int cookingDuration;
    final int deliveryDuration;

    public Order(int id, int cookingDuration, int deliveryDuration)
    {
        this.id = id;
        this.cookingDuration = cookingDuration;
        this.deliveryDuration = deliveryDuration;
    }
}
