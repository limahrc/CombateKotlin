package equipamentos

import personagens.Personagem

class Machado(private val dono: Personagem) : Arma(), ArmaCortante {
    override val DESGASTE = 10

    private val CORTE_TRANSVERSAL = 15f
    private val INVESTIDA = 10f

    override fun usar(atacante: Personagem, atacado: Personagem) {
        if (this.isUtil()) {
            val chance = dono.getChance(3)
            when (chance) {
                1 -> decaptar(atacado)
                2 -> atacado.defender(atacante, corteTransversal())
                else -> atacado.defender(atacante, investida())
            }
            desgastar()
        } else print("${getTipo()} não tem mais durabilidade")
    }

    override fun decaptar(p: Personagem) {
        print("FATALIDADE: ${dono.nome} decaptou ${p.nome} usando seu machado.")
        p.nivelEnergia = 0f
    }

    override fun corteTransversal(): Float {
        print("${dono.nome} deu um corte transversal com machado!")
        return CORTE_TRANSVERSAL
    }

    private fun investida(): Float {
        print("${dono.nome} deu uma investida com machado sobre o oponente!")
        return INVESTIDA
    }
}