import java.util.Scanner;

// Custom exception class for item not found
class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}

public class Shopping {
    public static void main(String[] args) {
        // Inicialização dos arrays (10 itens conforme solicitado)
        String[] items = new String[10];
        float[] prices = new float[10];

        items[0] = "Apple";   prices[0] = 0.50f;
        items[1] = "Banana";  prices[1] = 0.30f;
        items[2] = "Bread";   prices[2] = 2.00f;
        items[3] = "Milk";    prices[3] = 1.50f;
        items[4] = "Eggs";    prices[4] = 2.50f;
        items[5] = "Cheese";  prices[5] = 3.00f;
        items[6] = "Chicken"; prices[6] = 5.00f;
        items[7] = "Rice";    prices[7] = 1.00f;
        items[8] = "Pasta";   prices[8] = 1.20f;
        items[9] = "Tomato";  prices[9] = 0.80f;

        Scanner scanner = new Scanner(System.in);

        // Loop Externo: Roda enquanto o usuário não digitar "Exit"
        while (true) {
            System.out.println("\n--- Welcome to the Shop ---");
            System.out.println("Type the item name to buy, or 'Exit' to close the store.");
            
            float totalBill = 0.0f;
            boolean storeOpen = true;

            // Loop Interno: Adiciona itens ao carrinho
            while (true) {
                System.out.print("Enter item (or 'Finish' to see total): ");
                String inputItem = scanner.nextLine();

                // Verifica se deve sair do programa completamente
                if (inputItem.equalsIgnoreCase("Exit")) {
                    System.out.println("Your total is: " + totalBill);
                    storeOpen = false;
                    break;
                }

                // Verifica se encerrou a compra atual
                if (inputItem.equalsIgnoreCase("Finish")) {
                    break; 
                }

                try {
                    int itemIndex = -1;
                    // Busca o item no array
                    for (int i = 0; i < items.length; i++) {
                        if (items[i].equalsIgnoreCase(inputItem)) {
                            itemIndex = i;
                            break;
                        }
                    }

                    // Se não encontrou, lança a exceção
                    if (itemIndex == -1) {
                        throw new ItemNotFoundException("Item '" + inputItem + "' not found in our stock.");
                    }

                    // Se encontrou, recupera o preço e soma ao total
                    float price = prices[itemIndex];
                    totalBill += price;
                    System.out.println("Added " + items[itemIndex] + " - Price: $" + price);

                } catch (ItemNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            // Exibe o total se a loja ainda estiver "aberta" ou se terminou uma compra
            if (!storeOpen) {
                System.out.println("System closed. Goodbye!");
                break;
            } else {
                System.out.println("\n========================");
                System.out.printf("Your total bill is: $%.2f\n", totalBill);
                System.out.println("Thank you for shopping with us!");
                System.out.println("========================\n");
            }
        }
        
        scanner.close();
    }
}
