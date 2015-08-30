package components;

import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import utils.Utils;

public class Zoomer extends Component<Pane>{

	private static final double DEFAULT_ZOOM_FACTOR = 2000;
	private static final double DEFAULT_MAX = 3;
	private static final double DEFAULT_MIN = .05;
	
    private final Pane pane;
    private final double max;
    private final double min;
    private final boolean controlPressed;
    private final double zoomFactor;

    public Zoomer(Pane pane, double zoomFactor, double max, double min, boolean controlPressed) {
    	super(pane);
        this.pane = pane;
        this.max = max;
        this.min = min;
        this.controlPressed = controlPressed;
        this.zoomFactor = zoomFactor;
    }
    
    public Zoomer(Pane pane, double zoomFactor, double max, double min) {
    	this(pane, zoomFactor, max, min, true);
    }
    
    public Zoomer(Pane pane, double zoomFactor, boolean controlPressed) {
    	this(pane, zoomFactor, DEFAULT_MAX, DEFAULT_MIN, controlPressed);
    }
    
    public Zoomer(Pane pane,  double max, double min, boolean controlPressed) {
    	this(pane, DEFAULT_ZOOM_FACTOR, max, min, controlPressed);
    }
    
	public Zoomer(Pane pane, double zoomFactor) {
		this(pane, zoomFactor, DEFAULT_MAX, DEFAULT_MIN, true);    	
	}
	
	public Zoomer(Pane pane, double max, double min) {
		this(pane, DEFAULT_ZOOM_FACTOR, max, min, true);
	}
	
	public Zoomer(Pane pane, boolean controlPressed) {
		this(pane, DEFAULT_ZOOM_FACTOR, DEFAULT_MAX, DEFAULT_MIN, controlPressed);
	}
	
	public Zoomer(Pane pane) {
		this(pane, DEFAULT_ZOOM_FACTOR, DEFAULT_MAX, DEFAULT_MIN, true);
	}
    
	public Zoomer(IComponent<Pane> component, double zoomFactor, double max, double min, boolean controlPressed) {
		this(component.getNode(), zoomFactor, max, min, controlPressed);
    }
    
    public Zoomer(IComponent<Pane> component, double zoomFactor, double max, double min) {
    	this(component.getNode(), zoomFactor, max, min);
    }
    
    public Zoomer(IComponent<Pane> component, double zoomFactor, boolean controlPressed) {
    	this(component.getNode(), zoomFactor, controlPressed);
    }
    
    public Zoomer(IComponent<Pane> component,  double max, double min, boolean controlPressed) {
    	this(component.getNode(), max, min, controlPressed);
    }
    
	public Zoomer(IComponent<Pane> component, double zoomFactor) {
		this(component.getNode(), zoomFactor);    	
	}
	
	public Zoomer(IComponent<Pane> component, double max, double min) {
		this(component.getNode(), max, min);
	}
	
	public Zoomer(IComponent<Pane> component, boolean controlPressed) {
		this(component.getNode(), controlPressed);
	}
	
	public Zoomer(IComponent<Pane> component) {
		this(component.getNode());
	}
    
       
    @Override
    public void mount(){
    	EventHandler<? super ScrollEvent> oldHandler = pane.getOnScroll();
    	EventHandler<? super ScrollEvent> newHandler = (event)->{
    		if (event.isControlDown() == controlPressed) {
			   double scale = pane.getScaleX() + event.getDeltaY() / zoomFactor;

		        if (scale <= min)
		            scale = min;
		        
		        if (scale >= max)
		            scale = max;
		        
                pane.setScaleX(scale);
                pane.setScaleY(scale);
                event.consume();
            }
    	};
    	Utils.chain(oldHandler, newHandler);
    	pane.setOnScroll(newHandler);    	
    }
    
}
