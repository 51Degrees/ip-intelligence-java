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
import fiftyone.pipeline.engines.data.AspectData;
import fiftyone.pipeline.engines.data.AspectPropertyValue;
import java.util.List;
// This interface sits at the top of the name space in order to make
// life easier for consumers.
/**
 * Interface exposing typed accessors for properties related to an IP.
 * This includes the network, and location.
 */
public interface IPIntelligenceData extends AspectData
{
	/**
	 * Radius in kilometers of the circle centred around the most probable location that encompasses the entire area(s). See Areas property. This will likely be a very large distance. It is recommend to use the AccuracyRadiusMin property.
	 */
	AspectPropertyValue<List<IWeightedValue<Integer>>> getAccuracyRadiusMax();
	/**
	 * Radius in kilometers of the largest circle centred around the most probable location that fits within the area. Where multiple areas are returned, only the area that the most probable location falls within is considered. See Areas property.
	 */
	AspectPropertyValue<List<IWeightedValue<Integer>>> getAccuracyRadiusMin();
	/**
	 * Any shapes associated with the location. Usually this is the area which the IP range covers. This is returned as a WKT String stored as a reduced format of WKB.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getAreas();
	/**
	 * Autonomous System Number associated with the IP address.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getAsn();
	/**
	 * The name registered to the Asn associated with the IP address.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getAsnName();
	/**
	 * Indicates the type of connection being used. Returns either Broadband, Cellular, or Hosting and Anonymous.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getConnectionType();
	/**
	 * The 3-character ISO 3166-1 continent code for the supplied location.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getContinentCode2();
	/**
	 * The name of the continent the supplied location is in.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getContinentName();
	/**
	 * The name of the country that the supplied location is in.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getCountry();
	/**
	 * The 2-character ISO 3166-1 code of the country that the supplied location is in.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getCountryCode();
	/**
	 * The 3-character ISO 3166-1 alpha-3 code of the country that the supplied location is in.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getCountryCode3();
	/**
	 * The name of the county that the supplied location is in. In this case, a county is defined as an administrative sub-section of a country or state.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getCounty();
	/**
	 * The Alpha-3 ISO 4217 code of the currency associated with the supplied location.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getCurrencyCode();
	/**
	 * ITU international?telephone numbering plan code for the country.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getDialCode();
	/**
	 * The confidence that the IP address is a human user versus associated with hosting. A 1-10 value where; 1-3: Low confidence the user is human, 4-6: Medium confidence, 7-10: High confidence.
	 */
	AspectPropertyValue<List<IWeightedValue<Integer>>> getHumanProbability();
	/**
	 * End of the IP range to which the evidence IP belongs.
	 */
	AspectPropertyValue<List<IWeightedValue<InetAddress>>> getIpRangeEnd();
	/**
	 * Start of the IP range to which the evidence IP belongs.
	 */
	AspectPropertyValue<List<IWeightedValue<InetAddress>>> getIpRangeStart();
	/**
	 * Indicates whether the IP address is associated with a broadband connection. Includes DSL, Cable, Fibre, and Satellite connections.
	 */
	AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsBroadband();
	/**
	 * Indicates whether the IP address is associated with a cellular network.
	 */
	AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsCellular();
	/**
	 * Indicates whether the country of the supplied location is within the European Union.
	 */
	AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsEu();
	/**
	 * Indicates whether the IP address is associated with hosting. Includes both hosting and anonymised connections such as hosting networks, hosting ASNs, VPNs, proxies, TOR networks, and unreachable IP addresses.
	 */
	AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsHosted();
	/**
	 * Indicates whether the IP address is associated with a Proxy server.
	 */
	AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsProxy();
	/**
	 * Indicates whether the IP address is associated with a public router.
	 */
	AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsPublicRouter();
	/**
	 * Indicates whether the IP address is associated with a TOR server.
	 */
	AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsTor();
	/**
	 * Indicates whether the IP address is associated with a VPN server.
	 */
	AspectPropertyValue<List<IWeightedValue<Boolean>>> getIsVPN();
	/**
	 * The Alpha-2 ISO 639 Language code associated with the supplied location.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getLanguageCode();
	/**
	 * Average latitude of the IP. For privacy, this is randomized within around 1 kilometer of the result. Randomized result will change only once per day.
	 */
	AspectPropertyValue<List<IWeightedValue<Float>>> getLatitude();
	/**
	 * The confidence in the town and country provided.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getLocationConfidence();
	/**
	 * Average longitude of the IP. For privacy, this is randomized within around 1 kilometer of the result. Randomized result will change only once per day.
	 */
	AspectPropertyValue<List<IWeightedValue<Float>>> getLongitude();
	/**
	 * The mobile country code of the network the device is connected to.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getMcc();
	/**
	 * The name of the geographical region that the supplied location is in.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getRegion();
	/**
	 * Country code of the registered range.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getRegisteredCountry();
	/**
	 * Name of the IP range. This is usually the owner.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getRegisteredName();
	/**
	 * Registered owner of the range.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getRegisteredOwner();
	/**
	 * The name of the state that the supplied location is in.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getState();
	/**
	 * The name of the suburb that the supplied location is in.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getSuburb();
	/**
	 * The time zone at the supplied location in the IANA Time Zone format.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getTimeZoneIana();
	/**
	 * The offset from UTC in minutes in the supplied location, at the time that the value is produced.
	 */
	AspectPropertyValue<List<IWeightedValue<Integer>>> getTimeZoneOffset();
	/**
	 * The name of the town that the supplied location is in.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getTown();
	/**
	 * The zip or postal code that the supplied location falls under.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getZipCode();
}
