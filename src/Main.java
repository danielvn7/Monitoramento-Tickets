import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalTime;
import java.io.FileWriter;

void main() {
    ArrayList<String> filaMemoria = new ArrayList<>();
    int proximoId = 1;

    // String armazena textos. Aqui guardamos o seu nome.
    String nome = "Daniel Venancio";
    String cargo = "Técnico Interno";

    // 'int' armazena números inteiros. Definimos o nascimento e o ano atual.
    int anoNascimento = 1994;
    int anoAtual = 2026;
    int matriculaFuncionario = 304761;

    // Realizamos uma operação matemática básica e guardamos o resultado em uma nova variável.
    int idadeCalculada = anoAtual - anoNascimento;

    // System.out.println exibe informações no console (a tela de saída do programa).
    System.out.println("Relatório de Usuário:");
    System.out.println("Matricula: " + matriculaFuncionario);
    System.out.println("Nome: " + nome);
    System.out.println("Cargo: " + cargo);
    System.out.println("Idade: " + idadeCalculada + " anos");
    System.out.println("----------------------------------------");

    // Incluindo um Scanner temporário só para perguntar se quer ler agora, para não ler toda automatica.
    Scanner leitorTecladoRapido = new Scanner(System.in);
    System.out.print("Deseja carregar a planilha agora? (s/n): ");
    String resposta = leitorTecladoRapido.nextLine();

    if (resposta.equalsIgnoreCase("s")) {
        System.out.println("Daniel, iniciando busca manual de dados...");

        // (SISTEMA SEGUE LENDO A PLANILHA) CONFIGURAÇÃO DO ARQUIVO ---
        //    // Criamos um objeto do tipo 'File'. Ele funciona como um ponteiro indicando onde o arquivo está.
        //    // "src/chamados.csv" diz ao Java: "Entre na pasta src e procure o arquivo chamados.csv"
        File arquivoCsv = new File("src/chamados.csv");

        // O 'try' (tentar) protege o programa. Se o arquivo não existir, o Java não "quebra", ele pula para o 'catch'.
        try {
            // Criado um Scanner chamado 'leitorPlanilha'.
            // Passando o 'arquivoCsv' para ele, por isso ele lê o arquivo.
            Scanner leitorPlanilha = new Scanner(arquivoCsv);

            // O comando .hasNextLine() verifica se o arquivo tem pelo menos uma linha escrita.
            if (leitorPlanilha.hasNextLine()) {

                // Lemos a primeira linha (cabeçalho) e não guardamos em lugar nenhum.
                // Fazemos isso apenas para o cursor do Java pular para a linha dos dados reais.
                leitorPlanilha.nextLine();
            }

            System.out.println("=== PROCESSANDO CHAMADOS DA PLANILHA ===");

            // O 'while' (enquanto) criará um laço que só para quando o arquivo chegar na última linha.
            while (leitorPlanilha.hasNextLine()) {
                // .nextLine() pega a linha inteira
                String linha = leitorPlanilha.nextLine();

                // .split(",") quebra a String onde houver vírgula e guarda cada pedaço em uma "gaveta" (Array).
                String[] dados = linha.split(",");

                // Acessamos as "gavetas" do Array pelo índice (começando sempre do 0).
                String id = dados[0]; // ID do chamado
                String descricao = dados[1]; // O que aconteceu
                String prioridade = dados[2]; // Nível de urgência
                String solicitante = dados[3]; // Quem pediu ajuda

                // --- LÓGICA DO ID SEQUENCIAL ---
                // Converte o ID da planilha para número e vê se ele é o maior até agora
                int idAtual = Integer.parseInt(id);
                if (idAtual >= proximoId) {
                    proximoId = idAtual + 1; // O próximo será o maior encontrado + 1
                }

                filaMemoria.add("ID:" + id + " | [" + prioridade + "] - Sol: " + solicitante + " | Desc: " + descricao);

                System.out.println("Ticket #" + id + " [" + prioridade + "] - Solicitante: " + solicitante);
                System.out.println("Descrição: " + descricao);
                System.out.println("----------------------------------------");

                // Pausa de monitoramento (800ms)
                Thread.sleep(800);
            }

            // Finalizado o loop, fechamos o leitor para liberar o arquivo para outros programas.
            leitorPlanilha.close();

        } catch (Exception e) {
            // Se o arquivo sumir ou estiver com o nome errado, esta mensagem aparecerá.
            System.out.println("Aviso: Arquivo 'chamados.csv' não encontrado ou erro no formato.");
        }
    }
// --- INCLUINDO: MENU DE COMANDOS ---
        // Criando um novo Scanner para ler o que for digitado no teclado
        Scanner entradaTeclado = new Scanner(System.in);
        int opcao = -1;

        System.out.println("\n✅ Importação concluída! Entrando no modo de comando...");

        while (opcao != 0) {
            System.out.println("\n----------- MENU DE OPERAÇÃO -----------");
            System.out.println("1: Adicionar Novo Chamado");
            System.out.println("2: Ver Fila Atual");
            System.out.println("3: Atender Próximo (Remover)");
            System.out.println("0: Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            // Proteção simples caso o usuário não digite um número
            if (entradaTeclado.hasNextInt()) {
                opcao = entradaTeclado.nextInt();
                entradaTeclado.nextLine();

                if (opcao == 1) {
                    // --- MUDANÇA AQUI: Pedindo os campos conforme a planilha ---
                    System.out.print("DESCRIÇÃO: ");
                    String desc = entradaTeclado.nextLine();

                    System.out.print("PRIORIDADE (Baixa/Média/Alta): ");
                    String prio = entradaTeclado.nextLine();

                    System.out.print("SOLICITANTE: ");
                    String sol = entradaTeclado.nextLine();

                    // Geramos um ID para o CSV
                    String idGerado = String.valueOf(proximoId);


                    // 1. SALVA NA MEMÓRIA (RAM)
                    filaMemoria.add("ID:" + idGerado + " | [" + prio + "] - Sol: " + sol + " | Desc: " + desc);

                    // 2. SALVA NO ARQUIVO (DISCO) - O bloco FileWriter deve ficar AQUI dentro
                    try {
                        FileWriter escritor = new FileWriter("src/chamados.csv", true);
                        // Formato: ID,DESCRIÇÃO,PRIORIDADE,SOLICITANTE
                        escritor.write(idGerado + "," + desc + "," + prio + "," + sol + "\n");
                        escritor.close();
                        System.out.println(">>> ✅ Gravado no arquivo src/chamados.csv!");
                        proximoId++; // Soma +1 para o próximo chamado manual ser o 2, 3, 4...
                    } catch (Exception e) {
                        System.out.println(">>> ❌ Erro ao salvar no arquivo!");
                    }

                } else if (opcao == 2) {
                    System.out.println("\n--- FILA DE TRABALHO ATUAL ---");
                    if (filaMemoria.isEmpty()) {
                        System.out.println("Fila está limpa! Nenhum chamado pendente.");
                    } else {
                        for (int i = 0; i < filaMemoria.size(); i++) {
                            System.out.println((i + 1) + ". " + filaMemoria.get(i));
                        }
                    }

                } else if (opcao == 3) {
                    if (!filaMemoria.isEmpty()) {
                        String atendido = filaMemoria.remove(0);
                        System.out.println(">>> 🚀 Atendendo agora: " + atendido);
                    } else {
                        System.out.println(">>> ⚠️ Não há chamados na fila para atender!");
                    }
                }
            } else {
                System.out.println("Opção inválida! Digite um número.");
                entradaTeclado.next();
            }
        }


        // Exibe a mensagem final e usa LocalTime para registrar o horário exato do fim do processo.
        System.out.println("========================================");
        System.out.println("TUDO LIMPO! Processamento concluído às " + java.time.LocalTime.now());
    }
