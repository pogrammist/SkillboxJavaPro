public class Starter {
    public static void main(String[] args) {
        Integer dimaAge = 35;
        Integer mishaAge = 55;
        Integer vasyaAge = 18;

        Integer oldest = null;
        Integer middle = null;
        Integer youngest = null;

        oldest = dimaAge >= mishaAge ? (dimaAge >= vasyaAge ? dimaAge : vasyaAge) : (mishaAge >= vasyaAge ? mishaAge : vasyaAge);
        middle = dimaAge >= mishaAge ? (mishaAge >= vasyaAge ? mishaAge : vasyaAge) : (dimaAge >= vasyaAge ? dimaAge : vasyaAge);
        youngest = dimaAge > mishaAge ? (mishaAge > vasyaAge ? vasyaAge : mishaAge) : (dimaAge > vasyaAge ? vasyaAge : dimaAge);

//        if (dimaAge > mishaAge && dimaAge > vasyaAge)
//        {
//            oldest = dimaAge;
//            if (mishaAge > vasyaAge)
//            {
//                middle = mishaAge;
//                youngest = vasyaAge;
//            }
//            else
//            {
//                middle = vasyaAge;
//                youngest = mishaAge;
//            }
//        }
//        else if (mishaAge > dimaAge && mishaAge > vasyaAge)
//        {
//            oldest = mishaAge;
//            if (dimaAge > vasyaAge)
//            {
//                middle = dimaAge;
//                youngest = vasyaAge;
//            }
//            else
//            {
//                middle = vasyaAge;
//                youngest = dimaAge;
//            }
//        }
//        else
//        {
//            oldest = vasyaAge;
//            if (dimaAge > mishaAge)
//            {
//                middle = dimaAge;
//                youngest = mishaAge;
//            }
//            else
//            {
//                middle = mishaAge;
//                youngest = dimaAge;
//            }
//        }

        System.out.println("Most old: " + oldest);
        System.out.println("Middle: " + middle);
        System.out.println("Most young: " + youngest);
    }
}
