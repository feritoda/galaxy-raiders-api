@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = Math.sqrt(Math.pow(this.dx, 2.0) + Math.pow(this.dy, 2.0))

  val radiant: Double
    get() = 
      if(dy / madnitude < 0){
        - Math.abs(Math.acos(dx / magnitude))
      }
      else{
        Math.abs(Math.acos(dx / magnitude))
      }
  
  val degree: Double
    get() = Math.toDegrees(radiant)

  val unit: Vector2D
    get() = Vector2D(dx / magnitude, dy / magnitude)

  val normal: Vector2D
    get() = Vector2D(dy / magnitude, - dx / magnitude)

  operator fun times(scalar: Double): Vector2D {
    return Vector2D(dx * scalar, dy * scalar)
  }

  operator fun div(scalar: Double): Vector2D {
    return Vector2D(dx / scalar, dy / scalar)
  }

  operator fun times(v: Vector2D): Double {
    return v.dx * this.dx + v.dy * this.dy
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2D(v.dx +this.dx, v;dy + this.dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(p.x + this.dx, p.y + this.dy)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D(- dx, - dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(this.dx - v.dx, this.dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    return this * target / target.magnitude
  }

  fun vectorProject(target: Vector2D): Vector2D {
    return (this * target) / target.magnitude * (target.unit)
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return v * this
}
