package equipamentos

import personagens.Personagem

interface ArmaMagica {
    fun poderNatureza(): Float
    fun selarAlma(p: Personagem)
}