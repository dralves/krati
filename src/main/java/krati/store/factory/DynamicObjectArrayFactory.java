package krati.store.factory;

import java.io.IOException;

import krati.core.StoreConfig;
import krati.core.StoreFactory;
import krati.io.Serializer;
import krati.store.ArrayStore;
import krati.store.ObjectStore;
import krati.store.SerializableObjectArray;

/**
 * DynamicObjectArrayFactory
 * 
 * @author jwu
 * @since 10/11, 2011
 */
public class DynamicObjectArrayFactory<V> implements ObjectStoreFactory<Integer, V> {
    
    /**
     * Create an instance of {@link ObjectStore} for mapping keys to values.
     * The underlying store is backed by {@link krati.store.DynamicDataArray DynamicDataArray} to have better
     * performance where keys are integer.
     * 
     * @param config          - the configuration
     * @param keySerializer   - the serializer for keys
     * @param valueSerializer - the serializer for values
     * @return the newly created store
     * @throws IOException if the store cannot be created.
     */
    @Override
    public ObjectStore<Integer, V> create(StoreConfig config, Serializer<Integer> keySerializer, Serializer<V> valueSerializer) throws IOException {
        try {
            ArrayStore base = StoreFactory.createDynamicArrayStore(config);
            return new SerializableObjectArray<V>(base, keySerializer, valueSerializer);
        } catch (Exception e) {
            if(e instanceof IOException) {
                throw (IOException)e;
            } else {
                throw new IOException(e);
            }
        }
    }
}
