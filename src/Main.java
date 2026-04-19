//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    // String armazena textos. Aqui guardamos o seu nome.
    String nome = "Daniel Venancio";

    // 'int' armazena números inteiros. Definimos o nascimento e o ano atual.
    int anoNascimento = 1994;
    int anoAtual = 2026;

    // Realizamos uma operação matemática básica e guardamos o resultado em uma nova variável.
    int idadeCalculada = anoAtual - anoNascimento;

    // System.out.println exibe informações no console (a tela de saída do programa).
    System.out.println("Relatório de Usuário:");
    System.out.println("Nome: " + nome);
    System.out.println("Idade calculada: " + idadeCalculada + " anos");

    // Definimos a quantidade inicial de chamados (tickets) para a nossa automação.
    int ticketsAbertos = 5;

    System.out.println("=== SISTEMA DE MONITORAMENTO CHAMADOS ===");

    // 'while' cria um laço de repetição. Enquanto ticketsAbertos for maior que zero, o código dentro dele roda.
    while (ticketsAbertos > 0) {
        System.out.println("Chamados pendentes: " + ticketsAbertos + " | Status: Vigiando...");

        // '--' é o operador de decremento. Ele subtrai 1 da variável a cada repetição do loop.
        ticketsAbertos--;

        // Thread.sleep faz o programa "dormir" por 1000 milissegundos (1 segundo).
        // O 'try/catch' em volta é uma regra do Java para evitar que o programa trave caso ocorra um erro na pausa.
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    System.out.println("========================================");

    // LocalTime.now() chama uma função interna do Java que pega o horário exato do sistema.
    System.out.println("TUDO LIMPO! Fila zerada às " + java.time.LocalTime.now());
}

