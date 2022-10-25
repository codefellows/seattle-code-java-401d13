public class Main{
  // this is the executbale fn()!!
  public static void main(String[] args){
    System.out.println("This run");

      int int1 = 5;
      long long1 = 100;
      byte byte1 = (byte)0xFF;
      short short1 = (short)0xFFFF;
      boolean boolean1 = true;

      float float1 = 0.1f;
      float float2 = 0.2f;
      float float3 = float1 + float2;
      boolean booleanFloat = (float3 == 0.3f);  // true
      System.out.println("booleanFloat: " + booleanFloat);
      System.out.println("float3: " + float3);

      double double1 = 0.1;
      double double2 = 0.2;
      double double3 = double1 + double2;
      boolean booleanDouble = (double3 == 0.3);  // false!
      System.out.println("booleanDouble: " + booleanDouble);
      System.out.println("double3: " + double3);


      int int2 = 20;
      String numberStrin1 = "30";
      System.out.println("Int2 + numberstrin1: " + (int2 + numberStrin1));
      System.out.println("int2 + Integer.fromString(numberStrin1): " + (int2 + Integer.valueOf(numberStrin1)));

      //Conditionals
      //if-else statement
      int number1 = 7;
      int number2 = 10;
      if(number2 > number1){
        //condition 1 result
        System.out.println();
      }

      // Switch case
      int month = 21;
      String monthString;
      switch (month) {
         case 1:  monthString = "January";
        break;
      case 2:  monthString = "February";
        break;
      case 3:  monthString = "March";
        break;
      case 4:  monthString = "April";
        break;
      case 5:  monthString = "May";
        break;
      case 6:  monthString = "June";
        break;
      case 7:  monthString = "July";
        break;
      case 8:  monthString = "August";
        break;
      case 9:  monthString = "September";
        break;
      case 10: monthString = "October";
        break;
      case 11: monthString = "November";
        break;
      case 12: monthString = "December";
        break;
      default: monthString = "Invalid month";
        break;
      }
      System.out.println(monthString);

      // LOOPS
      
      // for loop - IF YOU KNOW THE LENGTH/AMOUNT OF ITERATIONS NEEDED
        int[] intArray1 = {3, 2, 5}; // -> [3,2,5]

        for(int i = 0; i < intArray1.length; i++)
        {
            System.out.println("intArray[" + i + "]: " + intArray1[i]);
        }

      // while loop - RUN UNTIL CONDITION MET
         int counter = 0;
          while (counter < 5)
          {
              System.out.println("counter: " + counter);
              counter++;
              if (counter > 2)
              {
                // break;
                continue;
              }
          }

      // for each - DATASET, APPLY LOGIC TO EACH INDEX
        for(int currentInt : intArray1)
        {
            System.out.println("currentInt: " + currentInt);
        }

        // 2 things that functions/methods do:
          // 1. Return something 
          // 2. Do something
        // Modifier return-Type NameOfFunction(PARAMETERS) {//Logic}
        public static int returnInt(int intArg)
        {
            return intArg;
        }
        returnInt(84);
      }
  }
}