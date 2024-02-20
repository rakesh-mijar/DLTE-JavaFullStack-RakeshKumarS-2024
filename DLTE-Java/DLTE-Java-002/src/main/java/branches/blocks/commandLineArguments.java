package branches.blocks;

import java.util.Scanner;

public class commandLineArguments {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        if(args.length ==  0){
            System.out.println("Enter the input as arguments");
        }
        for(String input:args){
            switch(input.toLowerCase()) {
                case "health":
                    System.out.println(input + " insurance is provided by LIC Insurance");
                    break;
                case "vehicle":
                    System.out.println(input + " insurance is provided by Mahindra Insurance");
                    break;
                case "life":
                    System.out.println(input + " insurance is provided by Bheema Insurance");
                    break;
                case "property":
                    System.out.println(input + " insurance is provided by Madhura Insurance");
                    break;
            }
        }
    }
}
