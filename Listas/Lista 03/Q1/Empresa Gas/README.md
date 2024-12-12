1.  Elabore  um  sistema  no  paradigma  OO  que  implemente  as  responsabilidades  de  uma 
empresa que entrega botijões de gás (suponha funcionar 24 horas).  
 
Crie a classe Pedido, descubra seus atributos e métodos. 
Implemente a classe Principal para um ArrayList de objetos pedido, onde seja possível testar 
os seguintes requisitos funcionais: 
  
a) O usuário seleciona no menu a opção "Fazer pedido" e o sistema solicita e insere no novo 
pedido a hora da compra e o endereço de entrega.  
b) O sistema informa os dados do pedido ao usuário e solicita que ele os confirme ou altere, se 
for o caso. Em caso de alteração, permite alterar apenas o endereço de entrega, altera no 
pedido e o exibe. Em caso de confirmação, o sistema solicita ao usuário que digite a quantidade 
de botijões que deseja e insere no pedido.  
c) O sistema calcula e insere no pedido o total da compra (pesquise o valor médio do preço do 
botijão na sua região para exibir ao usuário o preço unitário) e a hora de entrega para 6 horas 
corridas após a hora da compra (verificar mudança de dia), insere no pedido e informa estes 
dados.  
d) O sistema solicita o número do cartão de crédito e o insere no pedido como forma de 
pagamento. Em seguida marca como "confirmado" o status do pedido e exibe o código do 
pedido (número sequencial gerado automaticamente).  
e) Quando o pedido é entregue, o atendente seleciona a opção do menu “Confirmar entrega” 
que busca o pedido pelo código e, se encontrado, altera o seu status para “entregue”. Se não 
for encontrado, o sistema informa “Pedido não localizado”.  
f)  A  qualquer  momento,  o  usuário  pode  selecionar  no  menu  as  opções  “Ver  pedidos 
confirmados” ou “Ver pedidos entregues” para consultar todos os pedidos em aberto ou os 
atendidos, respectivamente. 