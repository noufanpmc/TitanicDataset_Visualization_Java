package Titanic.dataset;

import java.io.IOException;

import tech.tablesaw.aggregate.AggregateFunction;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.NumericColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.AreaPlot;
import tech.tablesaw.plotly.api.BubblePlot;
import tech.tablesaw.plotly.api.ScatterPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.HistogramTrace;
import tech.tablesaw.plotly.traces.PieTrace;
import tech.tablesaw.plotly.traces.ScatterTrace;

public class TitanicPlt {
	private static final AggregateFunction<?, ?> sum = null;
	private static IntColumn set;

	public static void main(String[] args) throws IOException {
		Table table = Table.read().csv("input.csv");
			 
		 System.out.println(table.columnNames());
		 System.out.println(table.structure());
		 System.out.println(table.first(3));
		 System.out.println(table.last(3));
		 
		//Histogram		
			Layout layout = Layout.builder().title("Distribution of Different Class").build();
		    HistogramTrace trace = HistogramTrace.builder(table.nCol("Pclass")).build();
		    Plot.show(new Figure(layout, trace));
		    
		 // Pie
		     Table t2 = table.countBy(table.categoricalColumn("Sex"));

			    PieTrace trace3 =
			        PieTrace.builder(t2.categoricalColumn("Category"), t2.numberColumn("Count")).build();
			    Layout layout3 = Layout.builder().title("Total percentage of Gender").build();

			    Plot.show(new Figure(layout3, trace3));
			    
	    //Scatter Plot
			 NumericColumn<?> x1 = table.numberColumn("Survived");
			 NumericColumn<?> y1 = table.numberColumn("Siblings/Spouses Aboard");
			 Layout layout1 = Layout.builder().title("Parents who had children and Survived or Not").build();
		     ScatterTrace trace1 = ScatterTrace.builder(x1, y1).mode(ScatterTrace.Mode.MARKERS).build();
		     Plot.show(new Figure(layout1, trace1));
		 
		 //Bubble chart 
		     Figure fig = BubblePlot.create("Survived People based on Fare and age",
	                table,// table name
	                "Survived",// x variable column name
	                "Fare",// y variable column name
	                "Age"// bubble size
	               );
	     	Plot.show(fig);
		 
		 //Area Chart
		 Plot.show(
			        AreaPlot.create(
			            "Area chart for Age and Fare", table, "age", "Fare"));
		 
			 

		 //Scatter
		 Table scatter =
			        table.where(
			            table
			                .numberColumn("Pclass")
			                .isEqualTo(1)
			                .and(table.numberColumn("Survived").isEqualTo(1)));

			    Plot.show(
			        ScatterPlot.create("Pclass 1, Age and Survived ", scatter, "Pclass", "Age"));
			    
			    
			    
			  }
	}
	
	


