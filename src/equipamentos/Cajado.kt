package equipamentos

import personagens.Personagem

class Cajado(val dono: Personagem) : Arma(), ArmaMagica {

    override val DESGASTE       = 10
    private val ATQ_CONGELANTE  = 13f
    private val ATQ_PLASMA      = 15f

    override fun usar(atacante: Personagem, atacado: Personagem) {
        if (this.isUtil()) {
            val chance = dono.getChance(3)
            when (chance) {
                1 -> selarAlma(atacado)
                2 -> atacado.defender(atacante, ataquePlasma())
                else -> atacado.defender(atacante, poderNatureza())
            }
            desgastar()
        } else print("${getTipo()} não tem mais durabilidade")
    }

    override fun poderNatureza(): Float {
        print("${dono.nome} disparou um ataque congelante com seu cajado!")
        return ATQ_CONGELANTE
    }

    private fun ataquePlasma(): Float {
        print("${dono.nome} disparou um raio de plasma do seu cajado!")
        return ATQ_PLASMA
    }

    override fun selarAlma(p: Personagem) {
        print("FATALIDADE: ${dono.nome} enviou a alma de ${p.nome} para o submundo.")
        p.nivelEnergia = 0f
    }





}