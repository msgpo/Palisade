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

package uk.gov.gchq.palisade.data.service.reader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import uk.gov.gchq.palisade.resource.Resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * An HdfsDataReader is an implementation of {@link SerialisedDataReader} for {@code HDFS}
 * that opens a file and returns a single {@link InputStream} containing all the records.
 * It must be configured with {@link uk.gov.gchq.palisade.data.serialise.Serialiser}s that are capable
 * of handling {@link InputStream}, such as {@link uk.gov.gchq.palisade.data.service.serialiser.AvroInputStreamSerialiser}.
 * </p>
 */
public class HdfsDataReader extends SerialisedDataReader {
    private final FileSystem fs;

    public HdfsDataReader(final Configuration conf) throws IOException {
        this(FileSystem.get(conf));
    }

    public HdfsDataReader(final FileSystem fs) {
        this.fs = fs;
    }

    @Override
    protected InputStream readRaw(final Resource resource) {
        final String resourceId = resource.getId();
        final Path path = new Path(resourceId);

        final InputStream inputStream;
        try {
            inputStream = fs.open(path);
        } catch (final IOException e) {
            throw new RuntimeException("Unable to read resource: " + resourceId, e);
        }

        return inputStream;
    }
}
