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
 * Interface exposing typed accessors for properties related to a device
 * returned by a device detection engine.
 */
public interface IPIntelligenceData extends AspectData
{
	/**
	 * Accuracy radius of the matched location in meters.
	 */
	AspectPropertyValue<List<IWeightedValue<Integer>>> getAccuracyRadius();
	/**
	 * Any shapes associated with the location. Usually this is the area which the IP range covers.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getAreas();
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
	 * End of the IP range to which the evidence IP belongs.
	 */
	AspectPropertyValue<List<IWeightedValue<InetAddress>>> getIpRangeEnd();
	/**
	 * Start of the IP range to which the evidence IP belongs.
	 */
	AspectPropertyValue<List<IWeightedValue<InetAddress>>> getIpRangeStart();
	/**
	 * Average latitude of the IP. For privacy, this is randomized within around 1 mile of the result. Randomized result will change only once per day.
	 */
	AspectPropertyValue<List<IWeightedValue<Float>>> getLatitude();
	/**
	 * Average longitude of the IP. For privacy, this is randomized within around 1 mile of the result. Randomized result will change only once per day.
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
	 * The name of the state that the supplied location in in.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getState();
	/**
	 * The offset from UTC in minutes in the supplied location, at the time that the value is produced.
	 */
	AspectPropertyValue<List<IWeightedValue<Integer>>> getTimeZoneOffset();
	/**
	 * The name of the town that the supplied location is in.
	 */
	AspectPropertyValue<List<IWeightedValue<String>>> getTown();
}
