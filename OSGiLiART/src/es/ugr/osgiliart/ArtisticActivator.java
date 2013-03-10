package es.ugr.osgiliart;

import javax.swing.JOptionPane;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ArtisticActivator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		ArtisticActivator.context = bundleContext;
		System.out.println("ART START");
		String filename = "/Users/anabpel/Documents/workspace/osgilath/osgiliath/OSGiLiART/scripts/collage-2.png";
		Histogram.Test(filename);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		ArtisticActivator.context = null;
	}

}