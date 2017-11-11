package equipamentos

import personagens.Personagem

class Livro(private val dono: Personagem) : Arma(), ArmaMagica {

    private val LANCA_CHAMAS = 25f

    override fun usar(atacante: Personagem, atacado: Personagem) {
        val chance = dono.getChance(3)
        when (chance) {
            1 -> selarAlma(atacado)
            2 -> mantoDefensivo()
            else -> atacado.defender(atacante, poderNatureza())
        }
        desgastar()
    }

    override val DESGASTE = 0

    private fun mantoDefensivo() {
        print("${dono.nome} conjurou um manto defensivo em si mesmo! DEFESA +10")
        dono.poderDefesa+=10
    }

    override fun poderNatureza(): Float {
        print("${dono.nome} disparou um ataque lança-chamas do seu livro de feitiços!")
        return LANCA_CHAMAS
    }

    override fun selarAlma(p: Personagem) {
        print("FATALIDADE: ${dono.nome} selou a alma de ${p.nome} no seu livro de feitiços.")
        p.nivelEnergia = 0f
    }
}