void main() {
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
    System.out.println("Daniel, iniciando busca automática de dados...");

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
            String id          = dados[0]; // ID do chamado
            String descricao   = dados[1]; // O que aconteceu
            String prioridade  = dados[2]; // Nível de urgência
            String solicitante = dados[3]; // Quem pediu ajuda

            // Acessamos as "gavetas" do Array pelo índice (começando sempre do 0).
            System.out.println("Ticket #" + dados[0] + " [" + dados[2] + "] - Solicitante: " + dados[3]);
            System.out.println("Descrição: " + dados[1]);
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

    // Exibe a mensagem final e usa LocalTime para registrar o horário exato do fim do processo.
    System.out.println("========================================");
    System.out.println("TUDO LIMPO! Processamento concluído às " + java.time.LocalTime.now());
}
