package equipamentos

import personagens.Personagem

interface ArmaCortante {

    fun decaptar(p: Personagem)

    fun corteTransversal(): Float

}