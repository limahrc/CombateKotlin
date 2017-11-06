package personagens

import equipamentos.Arma
import equipamentos.Cajado
import equipamentos.Espada
import java.util.*

abstract class Personagem(val nome: String) {

    var nivelEnergia    = 100f
    var poderDefesa     = 0
    var armas: MutableList<Arma> = mutableListOf()

    abstract val SOCO: Float
    abstract val CHUTE: Float
    val random = Random()

    abstract fun atacar(p: Personagem)

    fun defender(p: Personagem, poderAtaque: Float){
        if (p.isAlive()) {
            if (!esquivou()) {
                if (poderAtaque > this.poderDefesa) {
                    var dano = poderAtaque - this.poderDefesa
                    //if (dano >= nivelEnergia) nivelEnergia = 0f
                    //else nivelEnergia -= dano
                    if (dano >= nivelEnergia) dano = nivelEnergia
                    nivelEnergia -= dano
                    print(" ${nome.toUpperCase()} -$dano hp")
                } else {
                    println("$nome se defendeu do golpe de ${p.nome}!")
                }
            } else {
                print("\n${this.nome} esquivou do ataque de ${p.nome}! Nenhum dano!")
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

    fun hasArma(): Boolean {
        return armas.isNotEmpty()
    }

    fun gerarArma(dono: Personagem): Arma? {
        if (dono is Guerreiro) return Espada(dono)
        else if (dono is Feiticeiro) return Cajado(dono)
        return null
    }

    fun atacarIfHasArma(atacante: Personagem, atacado: Personagem) {
        val sortArma: Arma
        if (hasArma()) {
            sortArma = armas[random.nextInt(armas.count())]
            atacado.defender(atacante, sortArma.usar())
        }
        else atacado.defender(atacante, chutar())
    }

    fun quarterOfChance(): Boolean {
        return 1+random.nextInt()%4 == 1
    }

}