package personagens

import java.util.*

abstract class Personagem(val nome: String) {

    var nivelEnergia    = 100f
    var poderDefesa     = 0

    abstract val SOCO: Float
    abstract val CHUTE: Float
    val random = Random()

    abstract fun atacar(p: Personagem)

    fun defender(p: Personagem, poderAtaque: Float){
        if (p.isAlive()) {
            if (!esquivou()) {
                if (poderAtaque > this.poderDefesa) {
                    val dano = poderAtaque - this.poderDefesa
                    if (dano >= nivelEnergia) nivelEnergia = 0f
                    else nivelEnergia -= dano
                    print(" -$dano hp")
                } else {
                    println("$nome se defendeu do golpe de ${p.nome}!")
                }
            } else {
                print("\n${this.nome} esquivou do ataque! Nenhum dano!")
            }
        }
    }

    fun isAlive(): Boolean {
        return nivelEnergia>0
    }

    fun socar(): Float {
        print("$nome deu um soco!")
        return SOCO
    }

    fun chutar(): Float {
        print("$nome deu um chute!")
        return CHUTE
    }

    fun esquivou(): Boolean {
        val random = Random()
        return ((1 + random.nextInt()%4) == 1)
    }

    fun apresentar() {
        println("\nNOME: $nome\nENERGIA: $nivelEnergia\nDEFESA: $poderDefesa")
    }

    fun status(){
        println("\n${nome.toUpperCase()}: ($nivelEnergia hp)\n")
    }

    fun getChance(bound: Int): Int {
        return 1 + random.nextInt()%bound
    }

}