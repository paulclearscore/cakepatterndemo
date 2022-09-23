trait Config {
  final val started: Long =
    System.currentTimeMillis()
}

trait CommonConfig extends Config {
  def runConfig(): String = {
    println(s"Running CommonConfig - Timestamp: $started")
    "default-path"
  }
}

trait ConfigDietSlice {
  def configImpl: CommonConfig
}

trait ConfigSlice extends ConfigDietSlice {
  override val configImpl: CommonConfig = new CommonConfig {
    override def runConfig(): String = {
      println("**Config Slice**")
      println(
        s"Setting productApplyPath: $productApplyPath \n"
      )
      productApplyPath
    }
    val productApplyPath: String = "product-apply-service.path"

  }

}
