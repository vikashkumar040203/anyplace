/*
 * AnyPlace: A free and open Indoor Navigation Service with superb accuracy!
 *
 * Anyplace is a first-of-a-kind indoor information service offering GPS-less
 * localization, navigation and search inside buildings using ordinary smartphones.
 *
 * Author(s): Nikolas Neofytou, Constantinos Costa, Kyriakos Georgiou, Lambros Petrou
 *
 * Co-Supervisor: Paschalis Mpeis
 * Supervisor: Demetrios Zeinalipour-Yazti
 *
 * URL: https://anyplace.cs.ucy.ac.cy
 * Contact: anyplace@cs.ucy.ac.cy
 *
 * Copyright (c) 2016, Data Management Systems Lab (DMSL), University of Cyprus.
 * All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the “Software”), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */
package datasources

import java.io.FileOutputStream
import java.util.HashMap

import com.couchbase.client.java.document.json.JsonObject
import floor_module.IAlgo
import play.api.libs.json.JsValue
import utils.GeoPoint // TODO: Will use play.json

trait IDatasource {

  def register(collection: String, name: String, email: String, username: String, password: String,
               external: String, accType: String): JsValue

  def login(collection: String, username: String, password: String): List[JsValue]

  def createTimestampHeatmap(col: String, buid: String, floor: String, level: Int)

  def deleteFingerprint(fingerprint: JsValue): Boolean

  def poisByBuildingIDAsJson(buid: String): List[JsValue]

  def poisByBuildingAsJson2(cuid: String, letters: String): List[JsValue]

  def poisByBuildingAsJson2GR(cuid: String, letters: String): List[JsValue]

  def poisByBuildingAsJson3(buid: String, letters: String): List[JsValue]

  def init(): Boolean

  def addJsonDocument(key: String, expiry: Int, document: String): Boolean

  def addJsonDocument(col: String, document: String): Boolean

  def replaceJsonDocument(key: String, expiry: Int, document: String): Boolean

  def replaceJsonDocument(col: String, key: String, value: String, document: String): Boolean

  def deleteFromKey(col: String, key: String, value: String): Boolean

  def deleteFromKey(key: String): Boolean

  @deprecated("mdb")
  def getFromKey(key: String): AnyRef

  def getFromKey(collection: String, key: String, value: String): JsValue

  @deprecated("mdb")
  def getFromKeyAsJson(key: String): JsValue

  def getFromKeyAsJson(collection: String, key: String, value: String): JsValue

  def fingerprintExists(collection: String, buid: String, floor: String, x: String, y: String, heading: String): Boolean

  def buildingFromKeyAsJson(key: String): JsValue

  def poiFromKeyAsJson(collection: String, key: String, value: String): JsValue

  def poisByBuildingFloorAsJson(buid: String, floor_number: String): List[JsValue]

  def poisByBuildingFloorAsMap(buid: String, floor_number: String): java.util.List[HashMap[String, String]]

  def poisByBuildingAsJson(buid: String): java.util.List[JsValue]

  def poiByBuidFloorPuid(buid: String, floor_number: String, puid: String): Boolean

  def poisByBuildingAsMap(buid: String): java.util.List[HashMap[String, String]]

  def floorsByBuildingAsJson(buid: String): java.util.List[JsValue]

  def connectionsByBuildingAsJson(buid: String): List[JsValue]

  def connectionsByBuildingAsMap(buid: String): java.util.List[HashMap[String, String]]

  def connectionsByBuildingFloorAsJson(buid: String, floor_number: String): List[JsValue]

  def connectionsByBuildingAllFloorsAsJson(buid: String): List[JsValue]

  def deleteAllByBuilding(buid: String): Boolean

  def deleteAllByFloor(buid: String, floor_number: String): Boolean

  def deleteAllByConnection(cuid: String): java.util.List[String]

  def deleteAllByPoi(puid: String): java.util.List[String]

  def getRadioHeatmap(): java.util.List[JsonObject]

  def getRadioHeatmapByBuildingFloor(buid: String, floor: String): List[JsValue]

  def getRadioHeatmapByBuildingFloorAverage1(buid: String, floor: String): List[JsValue]

  def getRadioHeatmapByBuildingFloorAverage2(buid: String, floor: String): List[JsValue]

  /**
   * if heatmap do not exist, it creates them (heatmapWifi3).
   *
   * @param buid
   * @param floor
   * @return
   */
  def getRadioHeatmapByBuildingFloorAverage3(buid: String, floor: String): List[JsValue]

  def getRadioHeatmapByFloorTimestamp(buid: String, floor: String, timestampX: String, timestampY: String): List[JsValue]

  def getRadioHeatmapByBuildingFloorTimestampAverage1(buid: String, floor: String, timestampX: String, timestampY: String): List[JsValue]

  def getRadioHeatmapByBuildingFloorTimestampAverage2(buid: String, floor: String, timestampX: String, timestampY: String): List[JsValue]

  def getAPsByBuildingFloor(buid: String, floor: String): List[JsValue]

  def getAPsByBuildingFloorcdb(buid: String, floor: String): java.util.List[JsonObject]

  def getCachedAPsByBuildingFloor(buid: String, floor: String): JsValue

  def deleteAllByXsYs(buid: String, floor: String, x: String, y: String): java.util.List[String]

  def deleteAffectedHeatmaps(buid: String, floor_number: String): Boolean = ???

  def getFingerPrintsBBox(buid: String, floor: String, lat1: String, lon1: String, lat2: String, lon2: String): List[JsValue]

  def getFingerPrintsTimestampBBox(buid: String, floor: String, lat1: String, lon1: String, lat2: String, lon2: String, timestampX: String, timestampY: String): List[JsValue]

  def getFingerprintsByTime(buid: String, floor: String): List[JsValue]

  def getRadioHeatmapByBuildingFloor2(lat: String, lon: String, buid: String, floor: String, range: Int): java.util.List[JsonObject]

  def getRadioHeatmapBBox(lat: String, lon: String, buid: String, floor: String, range: Int): java.util.List[JsonObject]

  def getRadioHeatmapBBox2(lat: String, lon: String, buid: String, floor: String, range: Int): java.util.List[JsonObject]

  def getAllBuildings(): List[JsValue]

  def getAllBuildingsByOwner(oid: String): List[JsValue]

  def getAllBuildingsByBucode(bucode: String): List[JsValue]

  def getBuildingByAlias(alias: String): JsonObject

  def getAllBuildingsNearMe(lat: Double, lng: Double, range: Int, owner_id: String): List[JsValue]

  def dumpRssLogEntriesSpatial(outFile: FileOutputStream, bbox: Array[GeoPoint], floor_number: String): Long

  /**
   * Goes through each floor and finds the one that contains lat and lon.
   *
   * @param floor_number
   * @param lat
   * @param lon
   * @return the buid of the unique floor otherwise null.
   */
  def dumpRssLogEntriesWithCoordinates(floor_number: String, lat: Double, lon: Double): String

  /**
   * Populates rss-log per buid:floor.
   *
   * @param outFile rss-log file in frozen dir. It is filled per floor
   * @param buid
   * @param floor_number
   * @return
   */
  def dumpRssLogEntriesByBuildingFloor(outFile: FileOutputStream, buid: String, floor_number: String): Long

  def dumpRssLogEntriesByBuildingACCESFloor(outFile: FileOutputStream, buid: String, floor_number: String): Long

  def getAllAccounts(): List[JsValue]

  def predictFloor(algo: IAlgo, bbox: Array[GeoPoint], strongestMACs: Array[String]): Boolean

  def deleteRadiosInBox(): Boolean

  def magneticPathsByBuildingFloorAsJson(buid: String, floor_number: String): java.util.List[JsonObject]

  def magneticPathsByBuildingAsJson(buid: String): java.util.List[JsonObject]

  def magneticMilestonesByBuildingFloorAsJson(buid: String, floor_number: String): java.util.List[JsonObject]

  def BuildingSetsCuids(cuid: String): Boolean

  def getBuildingSet(cuid: String): List[JsValue]

  def getAllBuildingsetsByOwner(owner_id: String): List[JsValue]

  def generateHeatmaps(): Boolean

  def deleteNotValidDocuments(): Boolean

  def isAdmin(col: String): Boolean
}
