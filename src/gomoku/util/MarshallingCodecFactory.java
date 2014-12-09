package gomoku.util;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * Created by allen on 12/9/14.
 */
public final class MarshallingCodecFactory {

    public static MarshallingDecoder buildMarshallingDecoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration marshallingConfiguration = new MarshallingConfiguration();

        marshallingConfiguration.setVersion(5);
        UnmarshallerProvider unmarshallerProvider = new DefaultUnmarshallerProvider(marshallerFactory, marshallingConfiguration);
        MarshallingDecoder marshallingDecoder = new MarshallingDecoder(unmarshallerProvider, 1024);

        return marshallingDecoder;
    }   //buildMarshallingDecoder()

    public static MarshallingEncoder buildMarshallingEncoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration marshallingConfiguration = new MarshallingConfiguration();

        marshallingConfiguration.setVersion(5);
        MarshallerProvider marshallerProvider = new DefaultMarshallerProvider(marshallerFactory, marshallingConfiguration);
        MarshallingEncoder marshallingEncoder = new MarshallingEncoder(marshallerProvider);

        return marshallingEncoder;
    }   //buildMarshallingEncoder()

}   //MarshallingCodecFactory
