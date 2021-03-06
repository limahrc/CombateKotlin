package equipamentos

import personagens.Personagem

class Espada(val dono: Personagem) : Arma(), ArmaCortante {

    override val DESGASTE = 20

    private val CORTE_TRANSVERSAL   = 15f
    private val GOLPE_PERFURANTE    = 25f


    override fun usar(atacante: Personagem, atacado: Personagem) {
        if (this.isUtil()) {
            val chance = dono.getChance(3)
            when (chance) {
                1 -> decaptar(atacado)
                2 -> atacado.defender(atacante, corteTransversal())
                else -> atacado.defender(atacante, perfurar())
            }
            desgastar()
        } else print("${getTipo()} não tem mais durabilidade")
    }

    override fun decaptar(p: Personagem) {
        print("FATALIDADE: ${dono.nome} decaptou ${p.nome} usando seu machado.")
        p.nivelEnergia = 0f
    }

    override fun corteTransversal(): Float {
        print("${dono.nome} deu um corte transversal de espada!")
        return CORTE_TRANSVERSAL
    }

    private fun perfurar(): Float {
        print("${dono.nome} perfurou o oponente com a espada!")
        return GOLPE_PERFURANTE
    }

}