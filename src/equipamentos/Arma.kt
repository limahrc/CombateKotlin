package equipamentos

import personagens.Personagem

abstract class Arma(private val dono: Personagem) {

    var dano            = 0f
    var durabilidade    = 0f

    protected abstract val tipo: String
    private val DESGASTE = 10

    init {
        this.durabilidade = 100f
    }

    private fun isUtil(): Boolean {
        return durabilidade>0
    }

    fun usar(): Float {
        return if (isUtil()) {
            print("${dono.nome} deu um golpe de $tipo!")
            desgastar()
            dano
        }
        else {
            print("$tipo não tem mais durabilidade")
            0f
        }

    }

    private fun desgastar(){
        if (durabilidade>DESGASTE) {
            durabilidade -= DESGASTE
        } else durabilidade = 0f
    }

}
