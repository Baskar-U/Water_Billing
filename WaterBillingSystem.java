import java.util.Scanner;

class WaterBillingSystem{
    private int allotedCorporationWater;
    private int allotedBorewellWater;
    private int totalGuests;


    public void allotWater(int apartmentType, String ratio){
        int totalAllotedWater =(apartmentType == 2) ? 900 : 1500;

        String[] ratioParts = ratio.split(":");
        int corporationRatio = Integer.parseInt(ratioParts[0]);
        int borewellRatio = Integer.parseInt(ratioParts[1]);

        int totalRatio = corporationRatio + borewellRatio;

        allotedCorporationWater = Math.round((float)corporationRatio * totalAllotedWater / totalRatio);
        allotedBorewellWater = Math.round((float) borewellRatio * totalAllotedWater / totalRatio);

    }
    public void addGuests(int guests){
        totalGuests += guests;
    }
    private int calculateTankerCost(int tankerWater){
        if(tankerWater <= 500){
            return tankerWater * 2;
        }else if (tankerWater <= 1500){
            return 500 * 2 + (tankerWater - 500) * 3;
        } else if (tankerWater <= 3000){
            return 500 * 2 + 1000 * 3 + (tankerWater - 1500) * 5;
        } else {
            return 500 * 2 + 1000 * 3 + 1500 * 5 + (tankerWater - 3000) * 8;
        }
    }

    public void generateBill(){
        int baseWater = allotedCorporationWater + allotedBorewellWater;
        int guestsWater = totalGuests * 10 * 30;
        int totalConsumption = baseWater + guestsWater;

        int tankerWater = Math.max(0, totalConsumption - baseWater);
        int corporationCost = allotedCorporationWater * 1;
        int borewellCost = Math.round(allotedBorewellWater * 1.5f);
        int tankerCost = calculateTankerCost(tankerWater);

        int totalCost = corporationCost + borewellCost + tankerCost;
        System.out.println(totalConsumption + " " + totalCost);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WaterBillingSystem water = new WaterBillingSystem();

        while(true){
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");
            String command = parts[0];

            switch (command) {
                case "ALLOT_WATER":
                    water.allotWater(Integer.parseInt(parts[1]),parts[2]);
                    break;
                case "ADD_GUESTS":
                    water.addGuests(Integer.parseInt(parts[1]));
                    break;
                case "BILL":
                    water.generateBill();
                    return;
            }
        }

    
}
}