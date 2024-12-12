import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int option;
        int i = 0;
        ArrayList<Order>  orders = new ArrayList<Order>();

        do {
            System.out.println("Sistema da Empresa Gas");
            System.out.println("1 - Fazer Pedido");
            System.out.println("2 - Confirmar Entrega");
            System.out.println("3 - Ver Pedidos Entregues");
            System.out.println("4 - Listar todos os pedidos");
            System.out.println("0 - Sair");
            System.out.println("Selecione a Opcao desejada: ");
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Fazendo Pedido"); 
                    //1.a)
                    LocalTime currentTime = LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String orderTime = currentTime.format(formatter);
                    System.out.println("Digite o endereco de entrega:");
                    String shippingAdress = input.nextLine();
                    int confirm;
                    // 1.b)
                    do { 
                        System.out.println("Os dados inseridos estao corretos?");
                        System.out.println("Endereco: "+ shippingAdress );
                        System.out.println("Prescione 1 para SIM");
                        System.out.println("Prescione 2 para NAO");
                        confirm = input.nextInt();
                        input.nextLine();
                        switch (confirm) {
                            case 1:
                            System.out.println("Dados Confirmados");
                            break;
                            
                            case 2:
                            System.out.println("Digite o endereco de entrega:");
                            shippingAdress = input.nextLine();
                            break;
                            
                            default:
                            System.out.println("Opcao Invalida");
                            break;
                        }
                    } while (confirm !=1);
                    
                    System.out.println("Insira a quantidade de Botijoes que deseja:");
                    int qtyItens = input.nextInt(); 
                    qtyItens = Math.abs(qtyItens);
                    do {
                        System.out.println("Os dados inseridos estao corretos?");
                        System.out.println("Quantidade de Botijoes: "+ qtyItens );
                        System.out.println("Prescione 1 para SIM");
                        System.out.println("Prescione 2 para NAO");
                        confirm = input.nextInt();
                        switch (confirm) {
                            case 1:
                            System.out.println("Dados Confirmados");
                            break;
                            
                            case 2:
                                System.out.println("Insira a quantidade de Botijoes que deseja:");
                                qtyItens = input.nextInt(); 
                                qtyItens = Math.abs(qtyItens);
                                break;

                            default:
                                System.out.println("Opcao Invalida");
                                break;
                        }
                    input.nextLine();
                    } while (confirm !=1);
                    //1.c)
                    float totalOrder = qtyItens*100.00f; 
                    currentTime = currentTime.plusHours(6);
                    String deliveryTime = currentTime.format(formatter);
                    
                    System.out.println("Total do pedido:R$"+totalOrder );
                    System.out.println("Hora estimada de Entrega"+deliveryTime );
                    Order pOrder = new Order (shippingAdress, orderTime, deliveryTime, totalOrder, qtyItens);
                    orders.add(pOrder);
                    //1.d
                    System.out.println("Digite o numero do cartao de credito");
                    String creditCard = input.nextLine();
                    //Implementar filtro para padronizar entrada e converter em um formato aceito de cartão de crédito
                    pOrder.confirmOrder(orders.size(), creditCard);
                    break;
            
                case 2:
                    System.out.println("Confirmando Entrega");
                    pOrder = new Order();
                    System.out.println("Digite o ID do pedido que voce deseja confirmar a entrega:");
                    int search = input.nextInt();
                    input.nextLine();

                    boolean found = false;
                    for (i = 0; i < orders.size(); i++) {
                        pOrder = orders.get(i); 
                        if (search == pOrder.getId()) {
                            pOrder.confirmShipping(pOrder.getId());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Pedido não encontrado");
}
                    break;
                case 3:
                    if (orders.size() == 0){
                        System.out.println("Sem Pedidos Entregues");
                    } else {
                        for (i = 0; i < orders.size(); i++){
                            pOrder = new Order();
                            pOrder = orders.get(i);
                            if (pOrder.getStatus() == "Entregue") {
                                pOrder.showOrder(i);
                            }
                        }
                    }             
                break;
                case 4:
                    for (i = 0; i < orders.size(); i++){
                        pOrder = new Order();
                        pOrder = orders.get(i);
                        if (pOrder.getId() == 1000){
                            System.out.println("Sem pedidos");
                        } else {
                            pOrder.showOrder(i);
                        }
                    }
                    break;
                
                default:
                    System.out.println("Opcao Invalida");
                    break;
            }
        } while (option != 0);
        System.out.println("Finalizando Sistema");
        input.close();
    }
}
