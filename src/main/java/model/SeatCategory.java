package model;

public enum SeatCategory {
    ECONOMY("Economy"),
    PREMIUM_ECONOMY("Premium Economy"),
    BUSINESS("Business");

    public final String seatCategory;

    private SeatCategory(String seatCategory) {
        this.seatCategory = seatCategory;
    }

    public static SeatCategory getseatCategory(String seatCategory){

        for(SeatCategory category : SeatCategory.values()){
            if(category.seatCategory.equals(seatCategory)){
                return category;
            }
        }
        return null;
    }

}
