package net.edgwbs.wifi_localization.models

class WiFiInfo(
    val ssid: String,
    val capabilities: String,
    val frequency: Int,
    val level: Int,
    val timestamp: Long
) {
    fun viewFormat(): WiFiInfoViewFormat {
        return WiFiInfoViewFormat(
            ViewInfoCol("ssid: ", ssid),
            ViewInfoCol("capabilities: ", capabilities),
            ViewInfoCol("frequency: ", frequency.toString()),
            ViewInfoCol("level: ", level.toString()),
            ViewInfoCol("timestamp: ", timestamp.toString())
        )
    }
}

class WiFiInfoViewFormat(
    val ssid: ViewInfoCol,
    val capabilities: ViewInfoCol,
    val frequency: ViewInfoCol,
    val level: ViewInfoCol,
    val timestamp: ViewInfoCol
)

class ViewInfoCol(val label: String, val value: String)
