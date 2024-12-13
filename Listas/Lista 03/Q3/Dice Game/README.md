3. Desenvolva um Jogo de Dados no paradigma OO atendendo às seguintes regras de negócio: 
a) O jogo admite até 11 jogadores diferentes. Ler o nome e o valor que deseja apostar (entre 1 
e 12) 
b) O jogo não admite divisão do prêmio, portanto, não é possível duplicidade de apostas no 
mesmo número 
c) O jogo possui dois dados, numerados de 1 a 6 cada. 
d) Os dados são então lançados e os números surgem randomicamente na face de cada dado. 
Estes valores são então somados e exibe-se na tela o número sorteado. 
e) O jogo verifica se alguém venceu ou se a “máquina” venceu! 
f) Se alguém vencer, terá seus dados registrados em arquivo com a indicação de uma vitória 
g) Se a mesma pessoa jogar novamente e ganhar, o registro é atualizado para incrementar as 
vitórias deste jogador 
h) Na primeira tela do jogo (ao carregar) deve ser possível ver o ranking (caso haja) do TOP 
FIVE, com o nome e número de vitórias de cada ganhador. E ao apertar alguma tecla passa 
para a “tela” de coletar dados dos jogadores. 
i) Considere modelar o problema em UML com classes Dado, Player, Game etc.