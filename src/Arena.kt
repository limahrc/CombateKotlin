import personagens.*
import java.util.*

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
    if (lutador1.isAlive()) {
        println("O vencedor é ${lutador1.nome.toUpperCase()}!")
        if (isGuerreiro(lutador1, lutador2) || isFeiticeiro(lutador1, lutador2)) {
            if (lutador2.hasArma()) {
                lutador1.armas.addAll(lutador2.armas)
            }
        }
        lutador1.apresentar()
    }
    else {
        println("O vencedor é ${lutador2.nome.toUpperCase()}!")
        if (isGuerreiro(lutador1, lutador2) || isFeiticeiro(lutador1, lutador2)){
            if (lutador1.hasArma()) {
                lutador2.armas.addAll(lutador1.armas)
            }
        }
        lutador2.apresentar()
    }
}

fun isGuerreiro(a: Personagem, b: Personagem): Boolean {
    return a is Guerreiro && b is Guerreiro
}

fun isFeiticeiro(a: Personagem, b: Personagem): Boolean {
    return a is Feiticeiro && b is Feiticeiro
}

fun gerarLutador(nome: String): Personagem {
    val random = Random()
    return when (random.nextInt(3)){
        1 -> Guerreiro(nome)
        2 -> Feiticeiro(nome)
        else -> Besta(nome)
    }
}

fun main(args: Array<String>) {

    println("COMBATE MORTAL:")
    print("\nQuantas rodadas este torneio terá? ")
    val rodadas = readLine()!!.toDouble()
    val nPerson = Math.pow(2.0, rodadas)

    val lutadores: MutableList<Personagem> = mutableListOf()
    val ganhadores: MutableList<Personagem> = mutableListOf()

    var i = 0
    while (i < nPerson){
        print("Insira o nome do lutador ${i+1}: ")
        val nome = readLine()
        lutadores.add(gerarLutador(nome!!))
        i++
    }

    var aux = 0

    while(aux<rodadas){
        println("\nRodada #${aux+1}")
        i=0
        var x = 1
        while (i<lutadores.size){
            val e = i+1
            println("\nLuta #$x")
            batalhar(lutadores[i], lutadores[e])
            i+=2
            x++
        }
        i=0
        while (i<lutadores.size){
            if (lutadores[i].isAlive()) {
                ganhadores.add(lutadores[i])
                i++
            } else i++
        }
        lutadores.removeAll(lutadores)
        lutadores.addAll(ganhadores)
        ganhadores.removeAll(ganhadores)
        aux++
    }
}