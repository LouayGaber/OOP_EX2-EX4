package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * 
 * @author Louay 
 * 
 * Implementation of GIS_layer
 *
 */


public class GIS_layer_class extends HashSet<GIS_element> implements GIS_layer {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LayerMetaData MetaData = new LayerMetaData();

	@Override
	public Meta_data get_Meta_data() {
		return  this.MetaData;
	}

	
}