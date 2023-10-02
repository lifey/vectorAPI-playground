package playground

import jdk.incubator.vector.FloatVector
import jdk.incubator.vector.VectorSpecies


class ScalarComputation {
  fun scalarComputation(a: FloatArray, b: FloatArray, c: FloatArray) {
    for (i in a.indices) {
      c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f
    }
  }
  val SPECIES: VectorSpecies<Float> = FloatVector.SPECIES_PREFERRED

  fun vectorComputation(a: FloatArray, b: FloatArray, c: FloatArray) {
    var i = 0
    val upperBound: Int = SPECIES.loopBound(a.size)
    while (i < upperBound) {

      // FloatVector va, vb, vc;
      val va = FloatVector.fromArray(SPECIES, a, i)
      val vb = FloatVector.fromArray(SPECIES, b, i)
      val vc = va.mul(va)
        .add(vb.mul(vb))
        .neg()
      vc.intoArray(c, i)
      i += SPECIES.length()
    }
    while (i < a.size) {
      c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f
      i++
    }
  }
}
