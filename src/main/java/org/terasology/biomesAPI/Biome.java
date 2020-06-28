/*
 * Copyright 2018 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.biomesAPI;

import org.terasology.naming.Name;

/**
 * Biomes can be assigned to different blocks during worldgen as well as on runtime, to provide additional metadata
 * about player's surroundings usable to enhance player experience.
 * <p>
 * Biomes are easiest implemented in enums, and are meant to be implemented like that.
 *
 * @see OnBiomeChangedEvent
 */
public interface Biome {

    /**
     * @return An identifier that includes both the Module the biome originates from
     * and a unique biome id (unique to that module).
     */
    Name getId();

    /**
     * Returns human readable name of the biome.
     */
    String getDisplayName();

    /**
     * Biome hashCode must be deterministic, non-zero, and unique for every biome.
     * <p>
     * Please consider overriding this method to return constant values, hard-coded for each of the biomes.
     * No assumptions should however be made from any external module using biomes about their constant value,
     * i.e. modules should always retrieve biome hash using this function, and not hard-code any constant values.
     *
     * @return Hashcode of the biome
     */
    default short biomeHash() {
        short hash = 0;
        char[] chars = getId().toLowerCase().toCharArray();

        for (char c : chars) {
            hash = (short) (c + 31 * hash);
        }

        return hash;
    }


    /**
     * Gets the base humidity as a relative humidity value (between 0, or 0%, and 1, or 100%).
     *
     * This is primarily used for ClimateConditions, and should be overridden for each specific biome.
     *
     * @return The base humidity value of a biomes.
     */
    default float getHumidity() {
        return .3f;
    }

    /**
     * Gets the base temperature as a Celsius / 100 value (where 0 == 0 degrees and 1 == 100 degrees, for example).
     * There is no maximum or minimum value.
     *
     * This is primarily used for ClimateConditions, and should be overridden for each specific biome.
     *
     * @return The base temperature value of a biomes.
     */
    default float getTemperature() {
        return .22f;
    }
}
