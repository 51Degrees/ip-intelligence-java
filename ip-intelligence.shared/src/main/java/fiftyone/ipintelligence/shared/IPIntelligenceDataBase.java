/* *********************************************************************
 * This Original Work is copyright of 51 Degrees Mobile Experts Limited.
 * Copyright 2025 51 Degrees Mobile Experts Limited, Davidson House,
 * Forbury Square, Reading, Berkshire, United Kingdom RG1 3EU.
 *
 * This Original Work is licensed under the European Union Public Licence
 * (EUPL) v.1.2 and is subject to its terms as set out below.
 *
 * If a copy of the EUPL was not distributed with this file, You can obtain
 * one at https://opensource.org/licenses/EUPL-1.2.
 *
 * The 'Compatible Licences' set out in the Appendix to the EUPL (as may be
 * amended by the European Commission) shall be deemed incompatible for
 * the purposes of the Work and the provisions of the compatibility
 * clause in Article 5 of the EUPL shall not apply.
 * 
 * If using the Work as, or as part of, a network application, by 
 * including the attribution notice(s) required under Article 5 of the EUPL
 * in the end user terms of the application under an appropriate heading, 
 * such notice(s) shall fulfill the requirements of that article.
 * ********************************************************************* */

package fiftyone.ipintelligence.shared;
import java.net.InetAddress;
import fiftyone.pipeline.core.data.IWeightedValue;
import fiftyone.pipeline.core.data.FlowData;
import fiftyone.pipeline.engines.data.AspectData;
import fiftyone.pipeline.engines.data.AspectDataBase;
import fiftyone.pipeline.engines.data.AspectPropertyMetaData;
import fiftyone.pipeline.engines.flowelements.AspectEngine;
import fiftyone.pipeline.engines.data.AspectPropertyValue;
import fiftyone.pipeline.engines.services.MissingPropertyService;
import org.slf4j.Logger;
import java.util.List;
public abstract class IPIntelligenceDataBase extends AspectDataBase implements IPIntelligenceData
{
/**
 * Constructor.
 * @param logger used for logging
 * @param flowData the {@link FlowData} instance this element data will be
 *                 associated with
 * @param engine the engine which created the instance
 * @param missingPropertyService service used to determine the reason for
 *                               a property value being missing
 */
	protected IPIntelligenceDataBase(
		Logger logger,
		FlowData flowData,
		AspectEngine<? extends AspectData, ? extends AspectPropertyMetaData> engine,
		MissingPropertyService missingPropertyService) {
		super(logger, flowData, engine, missingPropertyService);
	}
	/**
	 * End of the IP range to which the evidence IP belongs.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<InetAddress>>> getIpRangeEnd() { return getAs("iprangeend", AspectPropertyValue.class, List.class, IWeightedValue.class, InetAddress.class); }
	/**
	 * Start of the IP range to which the evidence IP belongs.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<InetAddress>>> getIpRangeStart() { return getAs("iprangestart", AspectPropertyValue.class, List.class, IWeightedValue.class, InetAddress.class); }
	/**
	 * Country code of the registered range.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getRegisteredCountry() { return getAs("registeredcountry", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * Name of the IP range. This is usually the owner.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getRegisteredName() { return getAs("registeredname", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * Registered owner of the range.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getRegisteredOwner() { return getAs("registeredowner", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * Radius in kilometers of the circle centred around the most probable location that encompasses the entire area(s). See Areas property. This will likely be a very large distance. It is recommend to use the AccuracyRadiusMin property.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Integer>>> getAccuracyRadiusMax() { return getAs("accuracyradiusmax", AspectPropertyValue.class, List.class, IWeightedValue.class, Integer.class); }
	/**
	 * Radius in kilometers of the largest circle centred around the most probable location that fits within the area. Where multiple areas are returned, only the area that the most probable location falls within is considered. See Areas property.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Integer>>> getAccuracyRadiusMin() { return getAs("accuracyradiusmin", AspectPropertyValue.class, List.class, IWeightedValue.class, Integer.class); }
	/**
	 * Any shapes associated with the location. Usually this is the area which the IP range covers. This is returned as a WKT String stored as a reduced format of WKB.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getAreas() { return getAs("areas", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * Indicates the type of connection being used. Returns either Broadband, Cellular, or Hosting and Anonymous.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getConnectionType() { return getAs("connectiontype", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The 3-character ISO 3166-1 continent code for the supplied location.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getContinentCode2() { return getAs("continentcode2", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The name of the continent the supplied location is in.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getContinentName() { return getAs("continentname", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The name of the country that the supplied location is in.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getCountry() { return getAs("country", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The 2-character ISO 3166-1 code of the country that the supplied location is in.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getCountryCode() { return getAs("countrycode", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The 3-character ISO 3166-1 alpha-3 code of the country that the supplied location is in.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getCountryCode3() { return getAs("countrycode3", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The name of the county that the supplied location is in. In this case, a county is defined as an administrative sub-section of a country or state.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getCounty() { return getAs("county", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The Alpha-3 ISO 4217 code of the currency associated with the supplied location.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getCurrencyCode() { return getAs("currencycode", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * ITU international?telephone numbering plan code for the country.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getDialCode() { return getAs("dialcode", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The confidence that the IP address is a human user versus associated with hosting. A 1-10 value where; 1-3: Low confidence the user is human, 4-6: Medium confidence, 7-10: High confidence.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Integer>>> getHumanProbability() { return getAs("humanprobability", AspectPropertyValue.class, List.class, IWeightedValue.class, Integer.class); }
	/**
	 * Indicates whether the IP address is associated with a broadband connection. Includes DSL, Cable, Fibre, and Satellite connections.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsBroadband() { return getAs("isbroadband", AspectPropertyValue.class, List.class, IWeightedValue.class, Boolean.class); }
	/**
	 * Indicates whether the IP address is associated with a cellular network.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsCellular() { return getAs("iscellular", AspectPropertyValue.class, List.class, IWeightedValue.class, Boolean.class); }
	/**
	 * Indicates whether the country of the supplied location is within the European Union.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsEu() { return getAs("iseu", AspectPropertyValue.class, List.class, IWeightedValue.class, Boolean.class); }
	/**
	 * Indicates whether the IP address is associated with hosting. Includes both hosting and anonymised connections such as hosting networks, hosting ASNs, VPNs, proxies, TOR networks, and unreachable IP addresses.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsHosted() { return getAs("ishosted", AspectPropertyValue.class, List.class, IWeightedValue.class, Boolean.class); }
	/**
	 * Indicates whether the IP address is associated with a Proxy server.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsProxy() { return getAs("isproxy", AspectPropertyValue.class, List.class, IWeightedValue.class, Boolean.class); }
	/**
	 * Indicates whether the IP address is associated with a public router.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsPublicRouter() { return getAs("ispublicrouter", AspectPropertyValue.class, List.class, IWeightedValue.class, Boolean.class); }
	/**
	 * Indicates whether the IP address is associated with a TOR server.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsTor() { return getAs("istor", AspectPropertyValue.class, List.class, IWeightedValue.class, Boolean.class); }
	/**
	 * Indicates whether the IP address is associated with a VPN server.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsVPN() { return getAs("isvpn", AspectPropertyValue.class, List.class, IWeightedValue.class, Boolean.class); }
	/**
	 * The Alpha-2 ISO 639 Language code associated with the supplied location.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getLanguageCode() { return getAs("languagecode", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * Average latitude of the IP. For privacy, this is randomized within around 1 kilometer of the result. Randomized result will change only once per day.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Float>>> getLatitude() { return getAs("latitude", AspectPropertyValue.class, List.class, IWeightedValue.class, Float.class); }
	/**
	 * The confidence in the town and country provided.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getLocationConfidence() { return getAs("locationconfidence", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * Average longitude of the IP. For privacy, this is randomized within around 1 kilometer of the result. Randomized result will change only once per day.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Float>>> getLongitude() { return getAs("longitude", AspectPropertyValue.class, List.class, IWeightedValue.class, Float.class); }
	/**
	 * The name of the geographical region that the supplied location is in.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getRegion() { return getAs("region", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The name of the state that the supplied location is in.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getState() { return getAs("state", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The name of the suburb that the supplied location is in.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getSuburb() { return getAs("suburb", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The time zone at the supplied location in the IANA Time Zone format.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getTimeZoneIana() { return getAs("timezoneiana", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The offset from UTC in minutes in the supplied location, at the time that the value is produced.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<Integer>>> getTimeZoneOffset() { return getAs("timezoneoffset", AspectPropertyValue.class, List.class, IWeightedValue.class, Integer.class); }
	/**
	 * The name of the town that the supplied location is in.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getTown() { return getAs("town", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The zip or postal code that the supplied location falls under.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getZipCode() { return getAs("zipcode", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The mobile country code of the network the device is connected to.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getMcc() { return getAs("mcc", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * Autonomous System Number associated with the IP address.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getAsn() { return getAs("asn", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
	/**
	 * The name registered to the Asn associated with the IP address.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AspectPropertyValue<List<IWeightedValue<String>>> getAsnName() { return getAs("asnname", AspectPropertyValue.class, List.class, IWeightedValue.class, String.class); }
}
