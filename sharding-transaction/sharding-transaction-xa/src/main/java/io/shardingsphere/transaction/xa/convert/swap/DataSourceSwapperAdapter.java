/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
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
 * </p>
 */

package io.shardingsphere.transaction.xa.convert.swap;

import io.shardingsphere.core.config.DataSourceConfiguration;
import io.shardingsphere.core.rule.DataSourceParameter;
import lombok.Getter;

import javax.sql.DataSource;

/**
 * Abstract class for {@code DataSourceSwapper}.
 *
 * @author zhaojun
 */
@Getter
public abstract class DataSourceSwapperAdapter implements DataSourceSwapper {
    
    private final DataSourceConfiguration dataSourceConfiguration;
    
    private final AdvancedMapUpdater<String, Object> updater;
    
    DataSourceSwapperAdapter(final DataSource dataSource) {
        dataSourceConfiguration = DataSourceConfiguration.getDataSourceConfiguration(dataSource);
        updater = new AdvancedMapUpdater<>(dataSourceConfiguration.getProperties());
    }
    
    @Override
    public final DataSourceParameter swap() {
        convertProperties();
        return dataSourceConfiguration.createDataSourceParameter();
    }
    
    protected abstract void convertProperties();
}