import personagens.*
import java.util.*
import javax.swing.JOptionPane

fun batalhar(p1: Personagem, p2: Personagem){

    val lutador1: Personagem
    val lutador2: Personagem

    if (p1.getChance(2) == 1){
        lutador1 = p1
        lutador2 = p2
    } else {
        lutador1 = p2
        lutador2 = p1
    }

    println("Iniciando combate entre ${lutador1.nome} e ${lutador2.nome}!")
    println("Apresentando os combatentes:")
    lutador1.apresentar()
    lutador2.apresentar()

    println("\nQUE COMECE A LUTA!\n")

    while (lutador1.isAlive() && lutador2.isAlive()) {
        if (lutador1.isAlive()) {
            lutador1.atacar(lutador2)
            lutador2.status()
        } else break

        if (lutador2.isAlive()) {
            lutador2.atacar(lutador1)
            lutador1.status()
        } else break
    }
    if (lutador1.isAlive()) println("O vencedor é  ${lutador1.nome.toUpperCase()}!")
    else println("O vencedor é ${lutador2.nome.toUpperCase()}!")
}

fun gerarLutador(nome: String): Personagem {
    val random = Random()
    return when (1+random.nextInt()%3){
        1 -> Guerreiro(nome)
        2 -> Feiticeiro(nome)
        else -> Besta(nome)
    }
}

fun main(args: Array<String>) {

    print("Quantas rodadas este torneio terá? ")
    val rodadas = readLine()!!.toDouble()
    val nPerson = Math.pow(2.0, rodadas)

    val lutadores: MutableList<Personagem> = mutableListOf()

    var i = 0
    while (i < nPerson){
        print("Insira o nome do lutador ${i+1}: ")
        val nome = readLine()
        lutadores.add(gerarLutador(nome!!))
        i++
    }

    batalhar(lutadores[0], lutadores[1])
}