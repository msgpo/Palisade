/*
 * Copyright 2018 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.gchq.palisade.example.hrdatagenerator.types;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Random;

public class WorkLocation {
    private WorkLocationName workLocationName;
    private Address address;

    public WorkLocation() {
    }

    public WorkLocation(final WorkLocation workLocation) {
        workLocationName = workLocation.workLocationName;

        if (workLocation.address != null) {
            address = new Address(workLocation.address);
        }
    }

    public  WorkLocationName getWorkLocationName() {
        return workLocationName;
    }

    public void setWorkLocationName(final WorkLocationName workLocationName) {
        this.workLocationName = workLocationName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public static WorkLocation generate(final Faker faker, final Random random) {
        WorkLocation workLocation = new WorkLocation();
        workLocation.setAddress(Address.generate(faker, random));
        workLocation.setWorkLocationName(WorkLocationName.generate(random));
        return workLocation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("workLocationName", workLocationName)
                .append("address", address)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final WorkLocation workLocation = (WorkLocation) o;

        return new EqualsBuilder()
                .append(workLocationName, workLocation.workLocationName)
                .append(address, workLocation.address)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 41)
                .append(workLocationName)
                .append(address)
                .toHashCode();
    }
}


