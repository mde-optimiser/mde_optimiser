package org.xtext.example.mydsl.tests

class SolutionGenerator {
	
	private static String objective = '''
		minimize sum(energy)
	'''
	
	private static String solution = '''
					solution {
					 request0 -> implementation_0i1 {
					  compute_resource_0 -> resource0 {
					   cpu_0 -> cpu0_0
					   ram_1 -> ram0
					   disk_1 -> disk0
					   network_1 -> network0
					  }
					  the_component_0c0 --> implementation_0c0i0 {
					   compute_resource_0 -> resource1 {
					    cpu_0 -> cpu1_0
					    ram_1 -> ram1
					    disk_1 -> disk1
					    network_1 -> network1
					   }
					  }
					  the_component_0c1 --> implementation_0c1i0 {
					   compute_resource_0 -> resource2 {
					    cpu_0 -> cpu2_0
					    ram_1 -> ram2
					    disk_1 -> disk2
					    network_1 -> network2
					   }
					  }
					 }
					}
	'''
	
	private static String requests = '''
					request request0 for component_0 {
					 meta size = 144.0
					 requiring quality >= 96.0
					}
	'''
	
	private static String softwareModel = '''
					property total [MB]
					property free [MB]
					meta size
					property energy [J]
					property quality [%]
					component component_0 {
					 contract implementation_0i0 {
					  requires component the_component_0c0 of type component_0c0
					  requires component the_component_0c1 of type component_0c1
					  requires resource compute_resource_0 of type ComputeNode with {
					   cpu_0 of type CPU
					   ram_1 of type RAM
					   disk_1 of type DISK
					   network_1 of type NETWORK
					  }
					  requiring the_component_0c0.quality >= 89.0
					  requiring the_component_0c1.quality >= 71.0
					  requiring cpu_0.frequency >= 1858.0
					  requiring ram_1.total >= 2825.0
					  requiring disk_1.total >= 2707.0
					  requiring network_1.throughput >= 5414.0
					  providing quality = 6.0
					  providing energy = ((0.87*(size^2.0))+(0.98*cpu_0.frequency))
					  
					 }
					 contract implementation_0i1 {
					  requires component the_component_0c0 of type component_0c0
					  requires component the_component_0c1 of type component_0c1
					  requires resource compute_resource_0 of type ComputeNode with {
					   cpu_0 of type CPU
					   ram_1 of type RAM
					   disk_1 of type DISK
					   network_1 of type NETWORK
					  }
					  requiring the_component_0c0.quality >= 45.0
					  requiring the_component_0c1.quality >= 67.0
					  requiring cpu_0.frequency >= 1566.0
					  requiring ram_1.total >= 8601.0
					  requiring disk_1.total >= 15770.0
					  requiring network_1.throughput >= 80225.0
					  providing quality = 96.0
					  providing energy = ((0.9*(size^2.0))+(0.13*cpu_0.frequency))
					  
					 }
					 using property quality
					 using property energy
					}
					
					component component_0c0 {
					 contract implementation_0c0i0 {
					  requires resource compute_resource_0 of type ComputeNode with {
					   cpu_0 of type CPU
					   ram_1 of type RAM
					   disk_1 of type DISK
					   network_1 of type NETWORK
					  }
					  requiring cpu_0.frequency >= 1715.0
					  requiring ram_1.total >= 8262.0
					  requiring disk_1.total >= 8706.0
					  requiring network_1.throughput >= 33655.0
					  providing quality = 45.0
					  providing energy = ((0.31*(size^2.0))+(0.12*cpu_0.frequency))
					  
					 }
					 contract implementation_0c0i1 {
					  requires resource compute_resource_0 of type ComputeNode with {
					   cpu_0 of type CPU
					   ram_1 of type RAM
					   disk_1 of type DISK
					   network_1 of type NETWORK
					  }
					  requiring cpu_0.frequency >= 2290.0
					  requiring ram_1.total >= 8914.0
					  requiring disk_1.total >= 3658.0
					  requiring network_1.throughput >= 61931.0
					  providing quality = 65.0
					  providing energy = ((0.98*(size^2.0))+(0.88*cpu_0.frequency))
					  
					 }
					 using property quality
					 using property energy
					}
					
					component component_0c1 {
					 contract implementation_0c1i0 {
					  requires resource compute_resource_0 of type ComputeNode with {
					   cpu_0 of type CPU
					   ram_1 of type RAM
					   disk_1 of type DISK
					   network_1 of type NETWORK
					  }
					  requiring cpu_0.frequency >= 2038.0
					  requiring ram_1.total >= 8941.0
					  requiring disk_1.total >= 11052.0
					  requiring network_1.throughput >= 85378.0
					  providing quality = 86.0
					  providing energy = ((0.37*(size^2.0))+(0.71*cpu_0.frequency))
					  
					 }
					 contract implementation_0c1i1 {
					  requires resource compute_resource_0 of type ComputeNode with {
					   cpu_0 of type CPU
					   ram_1 of type RAM
					   disk_1 of type DISK
					   network_1 of type NETWORK
					  }
					  requiring cpu_0.frequency >= 2192.0
					  requiring ram_1.total >= 7448.0
					  requiring disk_1.total >= 15946.0
					  requiring network_1.throughput >= 6181.0
					  providing quality = 89.0
					  providing energy = ((0.1*(size^2.0))+(0.63*cpu_0.frequency))
					  
					 }
					 using property quality
					 using property energy
					}
	'''
	
	private static String hardwareModel = '''
						container resource type ComputeNode {
						 resource type CPU {
						  property frequency [Hz]
						  property load [%]
						 }
						 resource type RAM {
						  using property total
						  using property free
						 }
						 resource type DISK {
						  using property total
						  using property free
						 }
						 resource type NETWORK {
						  property latency [ms]
						  property throughput [kB/s]
						 }
						}
						resource resource4:ComputeNode {
						 resource cpu4_0:CPU {
						  frequency = 1284.0
						  load = 0.0
						 }
						 resource ram4:RAM {
						  total = 539.0
						  free = 539.0
						 }
						 resource disk4:DISK {
						  total = 8426.0
						  free = 8426.0
						 }
						 resource network4:NETWORK {
						  latency = 244.0
						  throughput = 8395.0
						 }
						}
						resource resource2:ComputeNode {
						 resource cpu2_0:CPU {
						  frequency = 2039.0
						  load = 0.0
						 }
						 resource ram2:RAM {
						  total = 8942.0
						  free = 8942.0
						 }
						 resource disk2:DISK {
						  total = 11053.0
						  free = 11053.0
						 }
						 resource network2:NETWORK {
						  latency = 663.0
						  throughput = 85379.0
						 }
						}
						resource resource3:ComputeNode {
						 resource cpu3_0:CPU {
						  frequency = 1749.0
						  load = 0.0
						 }
						 resource ram3:RAM {
						  total = 12552.0
						  free = 12552.0
						 }
						 resource disk3:DISK {
						  total = 9171.0
						  free = 9171.0
						 }
						 resource network3:NETWORK {
						  latency = 118.0
						  throughput = 57675.0
						 }
						}
						resource resource0:ComputeNode {
						 resource cpu0_0:CPU {
						  frequency = 1567.0
						  load = 0.0
						 }
						 resource ram0:RAM {
						  total = 8602.0
						  free = 8602.0
						 }
						 resource disk0:DISK {
						  total = 15771.0
						  free = 15771.0
						 }
						 resource network0:NETWORK {
						  latency = 518.0
						  throughput = 80226.0
						 }
						}
						resource resource1:ComputeNode {
						 resource cpu1_0:CPU {
						  frequency = 1716.0
						  load = 0.0
						 }
						 resource ram1:RAM {
						  total = 8263.0
						  free = 8263.0
						 }
						 resource disk1:DISK {
						  total = 8707.0
						  free = 8707.0
						 }
						 resource network1:NETWORK {
						  latency = 73.0
						  throughput = 33656.0
						 }
						}
	'''
	
	static def String getValidModel(){
		return String.format("%s%s%s%s%s", 
			SolutionGenerator.hardwareModel, 
			SolutionGenerator.softwareModel, 
			SolutionGenerator.requests,
			SolutionGenerator.objective, 
			SolutionGenerator.solution
		)
	}
	
	static def String getValidmodelCustomHardwareModel(String hardwareModel){
		return String.format("%s%s%s%s%s", 
			hardwareModel, 
			SolutionGenerator.softwareModel, 
			SolutionGenerator.requests, 
			SolutionGenerator.solution,
			SolutionGenerator.objective
		)
	}
	
	static def String getValidmodelCustomSoftwareModel(String softwareModel){
		return String.format("%s%s%s%s%s", 
			SolutionGenerator.hardwareModel, 
			softwareModel, 
			SolutionGenerator.requests, 
			SolutionGenerator.objective,
			SolutionGenerator.solution

		)
	}
	
	static def String getValidmodelCustomRequests(String requests){
		return String.format("%s%s%s%s%s", 
			SolutionGenerator.hardwareModel, 
			SolutionGenerator.softwareModel, 
			requests, 
			SolutionGenerator.objective,
			SolutionGenerator.solution
		)
	}
	
	static def String getValidmodelCustomSolution(String solution){
		return String.format("%s%s%s%s%s", 
			SolutionGenerator.hardwareModel, 
			SolutionGenerator.softwareModel, 
			SolutionGenerator.requests, 
			SolutionGenerator.objective,
			solution
		)
	}
		
	static def String getValidmodelCustomObjective(String objective){
		return String.format("%s%s%s%s%s", 
			SolutionGenerator.hardwareModel, 
			SolutionGenerator.softwareModel, 
			SolutionGenerator.requests, 
			objective,
			SolutionGenerator.solution
			
		)
	}
	
	
	static def String getInvalidModelWithTree(){
		
		return '''
container resource type ComputeNode {
 resource type CPU {
  property frequency [Hz]
  property load [%]
 }
 resource type RAM {
  using property total
  using property free
 }
 resource type DISK {
  using property total
  using property free
 }
 resource type NETWORK {
  property latency [ms]
  property throughput [kB/s]
 }
}
resource resource32:ComputeNode {
 resource cpu32_0:CPU {
  frequency = 2735.0
  load = 0.0
 }
 resource ram32:RAM {
  total = 9859.0
  free = 9859.0
 }
 resource disk32:DISK {
  total = 10829.0
  free = 10829.0
 }
 resource network32:NETWORK {
  latency = 168.0
  throughput = 14659.0
 }
}
resource resource0:ComputeNode {
 resource cpu0_0:CPU {
  frequency = 2476.0
  load = 0.0
 }
 resource ram0:RAM {
  total = 2179.0
  free = 2179.0
 }
 resource disk0:DISK {
  total = 2954.0
  free = 2954.0
 }
 resource network0:NETWORK {
  latency = 721.0
  throughput = 89823.0
 }
}
resource resource33:ComputeNode {
 resource cpu33_0:CPU {
  frequency = 2751.0
  load = 0.0
 }
 resource ram33:RAM {
  total = 9731.0
  free = 9731.0
 }
 resource disk33:DISK {
  total = 4216.0
  free = 4216.0
 }
 resource network33:NETWORK {
  latency = 897.0
  throughput = 83813.0
 }
}
resource resource2:ComputeNode {
 resource cpu2_0:CPU {
  frequency = 1196.0
  load = 0.0
 }
 resource ram2:RAM {
  total = 14913.0
  free = 14913.0
 }
 resource disk2:DISK {
  total = 8936.0
  free = 8936.0
 }
 resource network2:NETWORK {
  latency = 364.0
  throughput = 97895.0
 }
}
resource resource1:ComputeNode {
 resource cpu1_0:CPU {
  frequency = 2013.0
  load = 0.0
 }
 resource ram1:RAM {
  total = 4999.0
  free = 4999.0
 }
 resource disk1:DISK {
  total = 5118.0
  free = 5118.0
 }
 resource network1:NETWORK {
  latency = 87.0
  throughput = 14107.0
 }
}
resource resource42:ComputeNode {
 resource cpu42_0:CPU {
  frequency = 2722.0
  load = 0.0
 }
 resource ram42:RAM {
  total = 4763.0
  free = 4763.0
 }
 resource disk42:DISK {
  total = 7807.0
  free = 7807.0
 }
 resource network42:NETWORK {
  latency = 193.0
  throughput = 95799.0
 }
}
resource resource43:ComputeNode {
 resource cpu43_0:CPU {
  frequency = 1073.0
  load = 0.0
 }
 resource ram43:RAM {
  total = 12332.0
  free = 12332.0
 }
 resource disk43:DISK {
  total = 15114.0
  free = 15114.0
 }
 resource network43:NETWORK {
  latency = 891.0
  throughput = 65329.0
 }
}
resource resource3:ComputeNode {
 resource cpu3_0:CPU {
  frequency = 1859.0
  load = 0.0
 }
 resource ram3:RAM {
  total = 2826.0
  free = 2826.0
 }
 resource disk3:DISK {
  total = 2708.0
  free = 2708.0
 }
 resource network3:NETWORK {
  latency = 774.0
  throughput = 5415.0
 }
}
resource resource44:ComputeNode {
 resource cpu44_0:CPU {
  frequency = 1601.0
  load = 0.0
 }
 resource ram44:RAM {
  total = 1274.0
  free = 1274.0
 }
 resource disk44:DISK {
  total = 6523.0
  free = 6523.0
 }
 resource network44:NETWORK {
  latency = 400.0
  throughput = 10189.0
 }
}
resource resource36:ComputeNode {
 resource cpu36_0:CPU {
  frequency = 1322.0
  load = 0.0
 }
 resource ram36:RAM {
  total = 3911.0
  free = 3911.0
 }
 resource disk36:DISK {
  total = 11534.0
  free = 11534.0
 }
 resource network36:NETWORK {
  latency = 139.0
  throughput = 31944.0
 }
}
resource resource7:ComputeNode {
 resource cpu7_0:CPU {
  frequency = 1823.0
  load = 0.0
 }
 resource ram7:RAM {
  total = 11482.0
  free = 11482.0
 }
 resource disk7:DISK {
  total = 2318.0
  free = 2318.0
 }
 resource network7:NETWORK {
  latency = 419.0
  throughput = 60109.0
 }
}
resource resource37:ComputeNode {
 resource cpu37_0:CPU {
  frequency = 618.0
  load = 0.0
 }
 resource ram37:RAM {
  total = 12032.0
  free = 12032.0
 }
 resource disk37:DISK {
  total = 2135.0
  free = 2135.0
 }
 resource network37:NETWORK {
  latency = 407.0
  throughput = 19580.0
 }
}
resource resource38:ComputeNode {
 resource cpu38_0:CPU {
  frequency = 1375.0
  load = 0.0
 }
 resource ram38:RAM {
  total = 12312.0
  free = 12312.0
 }
 resource disk38:DISK {
  total = 12793.0
  free = 12793.0
 }
 resource network38:NETWORK {
  latency = 662.0
  throughput = 38510.0
 }
}
resource resource10:ComputeNode {
 resource cpu10_0:CPU {
  frequency = 1668.0
  load = 0.0
 }
 resource ram10:RAM {
  total = 15472.0
  free = 15472.0
 }
 resource disk10:DISK {
  total = 3555.0
  free = 3555.0
 }
 resource network10:NETWORK {
  latency = 567.0
  throughput = 51565.0
 }
}
resource resource16:ComputeNode {
 resource cpu16_0:CPU {
  frequency = 2172.0
  load = 0.0
 }
 resource ram16:RAM {
  total = 15824.0
  free = 15824.0
 }
 resource disk16:DISK {
  total = 1628.0
  free = 1628.0
 }
 resource network16:NETWORK {
  latency = 378.0
  throughput = 48407.0
 }
}
resource resource23:ComputeNode {
 resource cpu23_0:CPU {
  frequency = 1895.0
  load = 0.0
 }
 resource ram23:RAM {
  total = 5577.0
  free = 5577.0
 }
 resource disk23:DISK {
  total = 15275.0
  free = 15275.0
 }
 resource network23:NETWORK {
  latency = 595.0
  throughput = 93736.0
 }
}
resource resource17:ComputeNode {
 resource cpu17_0:CPU {
  frequency = 984.0
  load = 0.0
 }
 resource ram17:RAM {
  total = 7137.0
  free = 7137.0
 }
 resource disk17:DISK {
  total = 4485.0
  free = 4485.0
 }
 resource network17:NETWORK {
  latency = 101.0
  throughput = 69352.0
 }
}
resource resource13:ComputeNode {
 resource cpu13_0:CPU {
  frequency = 1219.0
  load = 0.0
 }
 resource ram13:RAM {
  total = 7027.0
  free = 7027.0
 }
 resource disk13:DISK {
  total = 926.0
  free = 926.0
 }
 resource network13:NETWORK {
  latency = 9.0
  throughput = 37782.0
 }
}
resource resource19:ComputeNode {
 resource cpu19_0:CPU {
  frequency = 680.0
  load = 0.0
 }
 resource ram19:RAM {
  total = 5011.0
  free = 5011.0
 }
 resource disk19:DISK {
  total = 3442.0
  free = 3442.0
 }
 resource network19:NETWORK {
  latency = 247.0
  throughput = 91479.0
 }
}
resource resource12:ComputeNode {
 resource cpu12_0:CPU {
  frequency = 2593.0
  load = 0.0
 }
 resource ram12:RAM {
  total = 9428.0
  free = 9428.0
 }
 resource disk12:DISK {
  total = 1541.0
  free = 1541.0
 }
 resource network12:NETWORK {
  latency = 146.0
  throughput = 56318.0
 }
}
resource resource45:ComputeNode {
 resource cpu45_0:CPU {
  frequency = 2760.0
  load = 0.0
 }
 resource ram45:RAM {
  total = 15146.0
  free = 15146.0
 }
 resource disk45:DISK {
  total = 3565.0
  free = 3565.0
 }
 resource network45:NETWORK {
  latency = 824.0
  throughput = 47221.0
 }
}
resource resource5:ComputeNode {
 resource cpu5_0:CPU {
  frequency = 2193.0
  load = 0.0
 }
 resource ram5:RAM {
  total = 7449.0
  free = 7449.0
 }
 resource disk5:DISK {
  total = 15947.0
  free = 15947.0
 }
 resource network5:NETWORK {
  latency = 101.0
  throughput = 6182.0
 }
}
resource resource25:ComputeNode {
 resource cpu25_0:CPU {
  frequency = 2894.0
  load = 0.0
 }
 resource ram25:RAM {
  total = 12248.0
  free = 12248.0
 }
 resource disk25:DISK {
  total = 3183.0
  free = 3183.0
 }
 resource network25:NETWORK {
  latency = 164.0
  throughput = 42189.0
 }
}
resource resource24:ComputeNode {
 resource cpu24_0:CPU {
  frequency = 2332.0
  load = 0.0
 }
 resource ram24:RAM {
  total = 15789.0
  free = 15789.0
 }
 resource disk24:DISK {
  total = 13861.0
  free = 13861.0
 }
 resource network24:NETWORK {
  latency = 877.0
  throughput = 72155.0
 }
}
resource resource26:ComputeNode {
 resource cpu26_0:CPU {
  frequency = 2357.0
  load = 0.0
 }
 resource ram26:RAM {
  total = 6924.0
  free = 6924.0
 }
 resource disk26:DISK {
  total = 13519.0
  free = 13519.0
 }
 resource network26:NETWORK {
  latency = 332.0
  throughput = 19193.0
 }
}
resource resource28:ComputeNode {
 resource cpu28_0:CPU {
  frequency = 1689.0
  load = 0.0
 }
 resource ram28:RAM {
  total = 1339.0
  free = 1339.0
 }
 resource disk28:DISK {
  total = 12051.0
  free = 12051.0
 }
 resource network28:NETWORK {
  latency = 32.0
  throughput = 62861.0
 }
}
resource resource46:ComputeNode {
 resource cpu46_0:CPU {
  frequency = 904.0
  load = 0.0
 }
 resource ram46:RAM {
  total = 14153.0
  free = 14153.0
 }
 resource disk46:DISK {
  total = 6637.0
  free = 6637.0
 }
 resource network46:NETWORK {
  latency = 1.0
  throughput = 62367.0
 }
}
resource resource22:ComputeNode {
 resource cpu22_0:CPU {
  frequency = 2701.0
  load = 0.0
 }
 resource ram22:RAM {
  total = 628.0
  free = 628.0
 }
 resource disk22:DISK {
  total = 9410.0
  free = 9410.0
 }
 resource network22:NETWORK {
  latency = 138.0
  throughput = 25322.0
 }
}
resource resource29:ComputeNode {
 resource cpu29_0:CPU {
  frequency = 1229.0
  load = 0.0
 }
 resource ram29:RAM {
  total = 6799.0
  free = 6799.0
 }
 resource disk29:DISK {
  total = 2077.0
  free = 2077.0
 }
 resource network29:NETWORK {
  latency = 481.0
  throughput = 19561.0
 }
}
resource resource21:ComputeNode {
 resource cpu21_0:CPU {
  frequency = 2800.0
  load = 0.0
 }
 resource ram21:RAM {
  total = 15647.0
  free = 15647.0
 }
 resource disk21:DISK {
  total = 6050.0
  free = 6050.0
 }
 resource network21:NETWORK {
  latency = 677.0
  throughput = 81343.0
 }
}
resource resource31:ComputeNode {
 resource cpu31_0:CPU {
  frequency = 647.0
  load = 0.0
 }
 resource ram31:RAM {
  total = 14070.0
  free = 14070.0
 }
 resource disk31:DISK {
  total = 990.0
  free = 990.0
 }
 resource network31:NETWORK {
  latency = 894.0
  throughput = 33554.0
 }
}
resource resource8:ComputeNode {
 resource cpu8_0:CPU {
  frequency = 1914.0
  load = 0.0
 }
 resource ram8:RAM {
  total = 11646.0
  free = 11646.0
 }
 resource disk8:DISK {
  total = 14083.0
  free = 14083.0
 }
 resource network8:NETWORK {
  latency = 880.0
  throughput = 25447.0
 }
}
resource resource40:ComputeNode {
 resource cpu40_0:CPU {
  frequency = 2641.0
  load = 0.0
 }
 resource ram40:RAM {
  total = 15943.0
  free = 15943.0
 }
 resource disk40:DISK {
  total = 9536.0
  free = 9536.0
 }
 resource network40:NETWORK {
  latency = 637.0
  throughput = 36711.0
 }
}
resource resource27:ComputeNode {
 resource cpu27_0:CPU {
  frequency = 2297.0
  load = 0.0
 }
 resource ram27:RAM {
  total = 10855.0
  free = 10855.0
 }
 resource disk27:DISK {
  total = 9919.0
  free = 9919.0
 }
 resource network27:NETWORK {
  latency = 792.0
  throughput = 46339.0
 }
}
resource resource20:ComputeNode {
 resource cpu20_0:CPU {
  frequency = 2541.0
  load = 0.0
 }
 resource ram20:RAM {
  total = 12108.0
  free = 12108.0
 }
 resource disk20:DISK {
  total = 4375.0
  free = 4375.0
 }
 resource network20:NETWORK {
  latency = 214.0
  throughput = 73620.0
 }
}
resource resource18:ComputeNode {
 resource cpu18_0:CPU {
  frequency = 2518.0
  load = 0.0
 }
 resource ram18:RAM {
  total = 12873.0
  free = 12873.0
 }
 resource disk18:DISK {
  total = 3266.0
  free = 3266.0
 }
 resource network18:NETWORK {
  latency = 864.0
  throughput = 57617.0
 }
}
resource resource15:ComputeNode {
 resource cpu15_0:CPU {
  frequency = 642.0
  load = 0.0
 }
 resource ram15:RAM {
  total = 4805.0
  free = 4805.0
 }
 resource disk15:DISK {
  total = 13593.0
  free = 13593.0
 }
 resource network15:NETWORK {
  latency = 180.0
  throughput = 68836.0
 }
}
resource resource30:ComputeNode {
 resource cpu30_0:CPU {
  frequency = 1771.0
  load = 0.0
 }
 resource ram30:RAM {
  total = 5517.0
  free = 5517.0
 }
 resource disk30:DISK {
  total = 3843.0
  free = 3843.0
 }
 resource network30:NETWORK {
  latency = 948.0
  throughput = 27207.0
 }
}
resource resource11:ComputeNode {
 resource cpu11_0:CPU {
  frequency = 2032.0
  load = 0.0
 }
 resource ram11:RAM {
  total = 3519.0
  free = 3519.0
 }
 resource disk11:DISK {
  total = 12100.0
  free = 12100.0
 }
 resource network11:NETWORK {
  latency = 545.0
  throughput = 29489.0
 }
}
resource resource14:ComputeNode {
 resource cpu14_0:CPU {
  frequency = 1302.0
  load = 0.0
 }
 resource ram14:RAM {
  total = 6760.0
  free = 6760.0
 }
 resource disk14:DISK {
  total = 6600.0
  free = 6600.0
 }
 resource network14:NETWORK {
  latency = 667.0
  throughput = 44543.0
 }
}
resource resource41:ComputeNode {
 resource cpu41_0:CPU {
  frequency = 2194.0
  load = 0.0
 }
 resource ram41:RAM {
  total = 691.0
  free = 691.0
 }
 resource disk41:DISK {
  total = 1693.0
  free = 1693.0
 }
 resource network41:NETWORK {
  latency = 457.0
  throughput = 36288.0
 }
}
resource resource9:ComputeNode {
 resource cpu9_0:CPU {
  frequency = 2830.0
  load = 0.0
 }
 resource ram9:RAM {
  total = 4180.0
  free = 4180.0
 }
 resource disk9:DISK {
  total = 15334.0
  free = 15334.0
 }
 resource network9:NETWORK {
  latency = 718.0
  throughput = 88832.0
 }
}
resource resource6:ComputeNode {
 resource cpu6_0:CPU {
  frequency = 2671.0
  load = 0.0
 }
 resource ram6:RAM {
  total = 15864.0
  free = 15864.0
 }
 resource disk6:DISK {
  total = 9372.0
  free = 9372.0
 }
 resource network6:NETWORK {
  latency = 381.0
  throughput = 65735.0
 }
}
resource resource39:ComputeNode {
 resource cpu39_0:CPU {
  frequency = 1931.0
  load = 0.0
 }
 resource ram39:RAM {
  total = 7843.0
  free = 7843.0
 }
 resource disk39:DISK {
  total = 6906.0
  free = 6906.0
 }
 resource network39:NETWORK {
  latency = 697.0
  throughput = 69742.0
 }
}
resource resource4:ComputeNode {
 resource cpu4_0:CPU {
  frequency = 1716.0
  load = 0.0
 }
 resource ram4:RAM {
  total = 8263.0
  free = 8263.0
 }
 resource disk4:DISK {
  total = 8707.0
  free = 8707.0
 }
 resource network4:NETWORK {
  latency = 984.0
  throughput = 33656.0
 }
}
resource resource34:ComputeNode {
 resource cpu34_0:CPU {
  frequency = 1055.0
  load = 0.0
 }
 resource ram34:RAM {
  total = 8702.0
  free = 8702.0
 }
 resource disk34:DISK {
  total = 1718.0
  free = 1718.0
 }
 resource network34:NETWORK {
  latency = 692.0
  throughput = 95415.0
 }
}
resource resource35:ComputeNode {
 resource cpu35_0:CPU {
  frequency = 1009.0
  load = 0.0
 }
 resource ram35:RAM {
  total = 10404.0
  free = 10404.0
 }
 resource disk35:DISK {
  total = 9811.0
  free = 9811.0
 }
 resource network35:NETWORK {
  latency = 610.0
  throughput = 64980.0
 }
}
property total [MB]
property free [MB]
meta size
property energy [J]
property quality [%]
component component_0 {
 contract implementation_0i0 {
  requires component the_component_0c0 of type component_0c0
  requires component the_component_0c1 of type component_0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0.quality >= 1.0
  requiring the_component_0c1.quality >= 13.0
  requiring cpu_0.frequency >= 2605.0
  requiring ram_1.total >= 6109.0
  requiring disk_1.total >= 13297.0
  requiring network_1.throughput >= 59432.0
  providing quality = 56.0
  providing energy = ((0.02*(size^2.0))+(0.62*cpu_0.frequency))
  
 }
 contract implementation_0i1 {
  requires component the_component_0c0 of type component_0c0
  requires component the_component_0c1 of type component_0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0.quality >= 68.0
  requiring the_component_0c1.quality >= 21.0
  requiring cpu_0.frequency >= 2475.0
  requiring ram_1.total >= 2178.0
  requiring disk_1.total >= 2953.0
  requiring network_1.throughput >= 89822.0
  providing quality = 68.0
  providing energy = ((0.52*(size^2.0))+(0.01*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0 {
 contract implementation_0c0i0 {
  requires component the_component_0c0c0 of type component_0c0c0
  requires component the_component_0c0c1 of type component_0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0.quality >= 35.0
  requiring the_component_0c0c1.quality >= 24.0
  requiring cpu_0.frequency >= 2012.0
  requiring ram_1.total >= 4998.0
  requiring disk_1.total >= 5117.0
  requiring network_1.throughput >= 14106.0
  providing quality = 68.0
  providing energy = ((0.59*(size^2.0))+(0.02*cpu_0.frequency))
  
 }
 contract implementation_0c0i1 {
  requires component the_component_0c0c0 of type component_0c0c0
  requires component the_component_0c0c1 of type component_0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0.quality >= 84.0
  requiring the_component_0c0c1.quality >= 2.0
  requiring cpu_0.frequency >= 1578.0
  requiring ram_1.total >= 2567.0
  requiring disk_1.total >= 1771.0
  requiring network_1.throughput >= 6541.0
  providing quality = 61.0
  providing energy = ((0.59*(size^2.0))+(0.94*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0 {
 contract implementation_0c0c0i0 {
  requires component the_component_0c0c0c0 of type component_0c0c0c0
  requires component the_component_0c0c0c1 of type component_0c0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0c0.quality >= 31.0
  requiring the_component_0c0c0c1.quality >= 41.0
  requiring cpu_0.frequency >= 2570.0
  requiring ram_1.total >= 3737.0
  requiring disk_1.total >= 7988.0
  requiring network_1.throughput >= 83381.0
  providing quality = 14.0
  providing energy = ((0.9*(size^2.0))+(0.29*cpu_0.frequency))
  
 }
 contract implementation_0c0c0i1 {
  requires component the_component_0c0c0c0 of type component_0c0c0c0
  requires component the_component_0c0c0c1 of type component_0c0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0c0.quality >= 75.0
  requiring the_component_0c0c0c1.quality >= 82.0
  requiring cpu_0.frequency >= 1195.0
  requiring ram_1.total >= 14912.0
  requiring disk_1.total >= 8935.0
  requiring network_1.throughput >= 97894.0
  providing quality = 72.0
  providing energy = ((0.5*(size^2.0))+(0.99*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0c0 {
 contract implementation_0c0c0c0i0 {
  requires component the_component_0c0c0c0c0 of type component_0c0c0c0c0
  requires component the_component_0c0c0c0c1 of type component_0c0c0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0c0c0.quality >= 89.0
  requiring the_component_0c0c0c0c1.quality >= 71.0
  requiring cpu_0.frequency >= 1858.0
  requiring ram_1.total >= 2825.0
  requiring disk_1.total >= 2707.0
  requiring network_1.throughput >= 5414.0
  providing quality = 75.0
  providing energy = ((0.87*(size^2.0))+(0.98*cpu_0.frequency))
  
 }
 contract implementation_0c0c0c0i1 {
  requires component the_component_0c0c0c0c0 of type component_0c0c0c0c0
  requires component the_component_0c0c0c0c1 of type component_0c0c0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0c0c0.quality >= 45.0
  requiring the_component_0c0c0c0c1.quality >= 67.0
  requiring cpu_0.frequency >= 1566.0
  requiring ram_1.total >= 8601.0
  requiring disk_1.total >= 15770.0
  requiring network_1.throughput >= 80225.0
  providing quality = 66.0
  providing energy = ((0.9*(size^2.0))+(0.13*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0c0c0 {
 contract implementation_0c0c0c0c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1715.0
  requiring ram_1.total >= 8262.0
  requiring disk_1.total >= 8706.0
  requiring network_1.throughput >= 33655.0
  providing quality = 89.0
  providing energy = ((0.31*(size^2.0))+(0.12*cpu_0.frequency))
  
 }
 contract implementation_0c0c0c0c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2290.0
  requiring ram_1.total >= 8914.0
  requiring disk_1.total >= 3658.0
  requiring network_1.throughput >= 61931.0
  providing quality = 65.0
  providing energy = ((0.98*(size^2.0))+(0.88*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0c0c1 {
 contract implementation_0c0c0c0c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2038.0
  requiring ram_1.total >= 8941.0
  requiring disk_1.total >= 11052.0
  requiring network_1.throughput >= 85378.0
  providing quality = 86.0
  providing energy = ((0.37*(size^2.0))+(0.71*cpu_0.frequency))
  
 }
 contract implementation_0c0c0c0c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2192.0
  requiring ram_1.total >= 7448.0
  requiring disk_1.total >= 15946.0
  requiring network_1.throughput >= 6181.0
  providing quality = 89.0
  providing energy = ((0.1*(size^2.0))+(0.63*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0c1 {
 contract implementation_0c0c0c1i0 {
  requires component the_component_0c0c0c1c0 of type component_0c0c0c1c0
  requires component the_component_0c0c0c1c1 of type component_0c0c0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0c1c0.quality >= 34.0
  requiring the_component_0c0c0c1c1.quality >= 45.0
  requiring cpu_0.frequency >= 2670.0
  requiring ram_1.total >= 15863.0
  requiring disk_1.total >= 9371.0
  requiring network_1.throughput >= 65734.0
  providing quality = 82.0
  providing energy = ((0.05*(size^2.0))+(0.4*cpu_0.frequency))
  
 }
 contract implementation_0c0c0c1i1 {
  requires component the_component_0c0c0c1c0 of type component_0c0c0c1c0
  requires component the_component_0c0c0c1c1 of type component_0c0c0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0c1c0.quality >= 35.0
  requiring the_component_0c0c0c1c1.quality >= 35.0
  requiring cpu_0.frequency >= 2216.0
  requiring ram_1.total >= 5576.0
  requiring disk_1.total >= 2226.0
  requiring network_1.throughput >= 66764.0
  providing quality = 90.0
  providing energy = ((0.79*(size^2.0))+(0.99*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0c1c0 {
 contract implementation_0c0c0c1c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1822.0
  requiring ram_1.total >= 11481.0
  requiring disk_1.total >= 2317.0
  requiring network_1.throughput >= 60108.0
  providing quality = 94.0
  providing energy = ((0.67*(size^2.0))+(0.06*cpu_0.frequency))
  
 }
 contract implementation_0c0c0c1c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1321.0
  requiring ram_1.total >= 7221.0
  requiring disk_1.total >= 12125.0
  requiring network_1.throughput >= 33612.0
  providing quality = 79.0
  providing energy = ((0.07*(size^2.0))+(0.35*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0c1c1 {
 contract implementation_0c0c0c1c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1913.0
  requiring ram_1.total >= 11645.0
  requiring disk_1.total >= 14082.0
  requiring network_1.throughput >= 25446.0
  providing quality = 45.0
  providing energy = ((0.74*(size^2.0))+(0.53*cpu_0.frequency))
  
 }
 contract implementation_0c0c0c1c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1561.0
  requiring ram_1.total >= 11493.0
  requiring disk_1.total >= 14106.0
  requiring network_1.throughput >= 70959.0
  providing quality = 27.0
  providing energy = ((0.85*(size^2.0))+(0.01*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1 {
 contract implementation_0c0c1i0 {
  requires component the_component_0c0c1c0 of type component_0c0c1c0
  requires component the_component_0c0c1c1 of type component_0c0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c1c0.quality >= 46.0
  requiring the_component_0c0c1c1.quality >= 73.0
  requiring cpu_0.frequency >= 2829.0
  requiring ram_1.total >= 4179.0
  requiring disk_1.total >= 15333.0
  requiring network_1.throughput >= 88831.0
  providing quality = 24.0
  providing energy = ((0.68*(size^2.0))+(0.3*cpu_0.frequency))
  
 }
 contract implementation_0c0c1i1 {
  requires component the_component_0c0c1c0 of type component_0c0c1c0
  requires component the_component_0c0c1c1 of type component_0c0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c1c0.quality >= 36.0
  requiring the_component_0c0c1c1.quality >= 6.0
  requiring cpu_0.frequency >= 575.0
  requiring ram_1.total >= 5309.0
  requiring disk_1.total >= 2210.0
  requiring network_1.throughput >= 87023.0
  providing quality = 14.0
  providing energy = ((0.75*(size^2.0))+(0.19*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1c0 {
 contract implementation_0c0c1c0i0 {
  requires component the_component_0c0c1c0c0 of type component_0c0c1c0c0
  requires component the_component_0c0c1c0c1 of type component_0c0c1c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c1c0c0.quality >= 89.0
  requiring the_component_0c0c1c0c1.quality >= 27.0
  requiring cpu_0.frequency >= 1667.0
  requiring ram_1.total >= 15471.0
  requiring disk_1.total >= 3554.0
  requiring network_1.throughput >= 51564.0
  providing quality = 53.0
  providing energy = ((0.31*(size^2.0))+(0.94*cpu_0.frequency))
  
 }
 contract implementation_0c0c1c0i1 {
  requires component the_component_0c0c1c0c0 of type component_0c0c1c0c0
  requires component the_component_0c0c1c0c1 of type component_0c0c1c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c1c0c0.quality >= 77.0
  requiring the_component_0c0c1c0c1.quality >= 58.0
  requiring cpu_0.frequency >= 2010.0
  requiring ram_1.total >= 6979.0
  requiring disk_1.total >= 9786.0
  requiring network_1.throughput >= 51477.0
  providing quality = 95.0
  providing energy = ((0.65*(size^2.0))+(0.5*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1c0c0 {
 contract implementation_0c0c1c0c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2031.0
  requiring ram_1.total >= 3518.0
  requiring disk_1.total >= 12099.0
  requiring network_1.throughput >= 29488.0
  providing quality = 95.0
  providing energy = ((0.79*(size^2.0))+(0.0*cpu_0.frequency))
  
 }
 contract implementation_0c0c1c0c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1675.0
  requiring ram_1.total >= 14329.0
  requiring disk_1.total >= 13348.0
  requiring network_1.throughput >= 34862.0
  providing quality = 3.0
  providing energy = ((0.99*(size^2.0))+(0.77*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1c0c1 {
 contract implementation_0c0c1c0c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2592.0
  requiring ram_1.total >= 9427.0
  requiring disk_1.total >= 1540.0
  requiring network_1.throughput >= 56317.0
  providing quality = 68.0
  providing energy = ((0.74*(size^2.0))+(0.73*cpu_0.frequency))
  
 }
 contract implementation_0c0c1c0c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2645.0
  requiring ram_1.total >= 11224.0
  requiring disk_1.total >= 15330.0
  requiring network_1.throughput >= 61842.0
  providing quality = 4.0
  providing energy = ((0.09*(size^2.0))+(0.85*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1c1 {
 contract implementation_0c0c1c1i0 {
  requires component the_component_0c0c1c1c0 of type component_0c0c1c1c0
  requires component the_component_0c0c1c1c1 of type component_0c0c1c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c1c1c0.quality >= 94.0
  requiring the_component_0c0c1c1c1.quality >= 82.0
  requiring cpu_0.frequency >= 1039.0
  requiring ram_1.total >= 14989.0
  requiring disk_1.total >= 7301.0
  requiring network_1.throughput >= 15856.0
  providing quality = 100.0
  providing energy = ((0.17*(size^2.0))+(0.35*cpu_0.frequency))
  
 }
 contract implementation_0c0c1c1i1 {
  requires component the_component_0c0c1c1c0 of type component_0c0c1c1c0
  requires component the_component_0c0c1c1c1 of type component_0c0c1c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c1c1c0.quality >= 2.0
  requiring the_component_0c0c1c1c1.quality >= 46.0
  requiring cpu_0.frequency >= 1218.0
  requiring ram_1.total >= 7026.0
  requiring disk_1.total >= 925.0
  requiring network_1.throughput >= 37781.0
  providing quality = 73.0
  providing energy = ((0.31*(size^2.0))+(0.54*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1c1c0 {
 contract implementation_0c0c1c1c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1301.0
  requiring ram_1.total >= 6759.0
  requiring disk_1.total >= 6599.0
  requiring network_1.throughput >= 44542.0
  providing quality = 75.0
  providing energy = ((0.38*(size^2.0))+(0.55*cpu_0.frequency))
  
 }
 contract implementation_0c0c1c1c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 857.0
  requiring ram_1.total >= 11845.0
  requiring disk_1.total >= 8196.0
  requiring network_1.throughput >= 30833.0
  providing quality = 65.0
  providing energy = ((0.7*(size^2.0))+(0.75*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1c1c1 {
 contract implementation_0c0c1c1c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 641.0
  requiring ram_1.total >= 4804.0
  requiring disk_1.total >= 13592.0
  requiring network_1.throughput >= 68835.0
  providing quality = 56.0
  providing energy = ((0.1*(size^2.0))+(0.34*cpu_0.frequency))
  
 }
 contract implementation_0c0c1c1c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1565.0
  requiring ram_1.total >= 3190.0
  requiring disk_1.total >= 587.0
  requiring network_1.throughput >= 10681.0
  providing quality = 75.0
  providing energy = ((0.89*(size^2.0))+(0.3*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1 {
 contract implementation_0c1i0 {
  requires component the_component_0c1c0 of type component_0c1c0
  requires component the_component_0c1c1 of type component_0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0.quality >= 35.0
  requiring the_component_0c1c1.quality >= 44.0
  requiring cpu_0.frequency >= 2171.0
  requiring ram_1.total >= 15823.0
  requiring disk_1.total >= 1627.0
  requiring network_1.throughput >= 48406.0
  providing quality = 46.0
  providing energy = ((0.76*(size^2.0))+(0.77*cpu_0.frequency))
  
 }
 contract implementation_0c1i1 {
  requires component the_component_0c1c0 of type component_0c1c0
  requires component the_component_0c1c1 of type component_0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0.quality >= 88.0
  requiring the_component_0c1c1.quality >= 64.0
  requiring cpu_0.frequency >= 2724.0
  requiring ram_1.total >= 6670.0
  requiring disk_1.total >= 13598.0
  requiring network_1.throughput >= 79810.0
  providing quality = 78.0
  providing energy = ((0.0*(size^2.0))+(0.27*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0 {
 contract implementation_0c1c0i0 {
  requires component the_component_0c1c0c0 of type component_0c1c0c0
  requires component the_component_0c1c0c1 of type component_0c1c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0c0.quality >= 47.0
  requiring the_component_0c1c0c1.quality >= 44.0
  requiring cpu_0.frequency >= 893.0
  requiring ram_1.total >= 6752.0
  requiring disk_1.total >= 6691.0
  requiring network_1.throughput >= 19933.0
  providing quality = 14.0
  providing energy = ((0.41*(size^2.0))+(0.05*cpu_0.frequency))
  
 }
 contract implementation_0c1c0i1 {
  requires component the_component_0c1c0c0 of type component_0c1c0c0
  requires component the_component_0c1c0c1 of type component_0c1c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0c0.quality >= 8.0
  requiring the_component_0c1c0c1.quality >= 88.0
  requiring cpu_0.frequency >= 983.0
  requiring ram_1.total >= 7136.0
  requiring disk_1.total >= 4484.0
  requiring network_1.throughput >= 69351.0
  providing quality = 35.0
  providing energy = ((0.83*(size^2.0))+(0.54*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0c0 {
 contract implementation_0c1c0c0i0 {
  requires component the_component_0c1c0c0c0 of type component_0c1c0c0c0
  requires component the_component_0c1c0c0c1 of type component_0c1c0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0c0c0.quality >= 70.0
  requiring the_component_0c1c0c0c1.quality >= 43.0
  requiring cpu_0.frequency >= 868.0
  requiring ram_1.total >= 12108.0
  requiring disk_1.total >= 13067.0
  requiring network_1.throughput >= 93140.0
  providing quality = 68.0
  providing energy = ((0.04*(size^2.0))+(0.59*cpu_0.frequency))
  
 }
 contract implementation_0c1c0c0i1 {
  requires component the_component_0c1c0c0c0 of type component_0c1c0c0c0
  requires component the_component_0c1c0c0c1 of type component_0c1c0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0c0c0.quality >= 27.0
  requiring the_component_0c1c0c0c1.quality >= 92.0
  requiring cpu_0.frequency >= 2517.0
  requiring ram_1.total >= 12872.0
  requiring disk_1.total >= 3265.0
  requiring network_1.throughput >= 57616.0
  providing quality = 8.0
  providing energy = ((0.91*(size^2.0))+(0.02*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0c0c0 {
 contract implementation_0c1c0c0c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 679.0
  requiring ram_1.total >= 5010.0
  requiring disk_1.total >= 3441.0
  requiring network_1.throughput >= 91478.0
  providing quality = 77.0
  providing energy = ((0.52*(size^2.0))+(0.2*cpu_0.frequency))
  
 }
 contract implementation_0c1c0c0c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2611.0
  requiring ram_1.total >= 5126.0
  requiring disk_1.total >= 14183.0
  requiring network_1.throughput >= 62309.0
  providing quality = 49.0
  providing energy = ((0.19*(size^2.0))+(0.3*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0c0c1 {
 contract implementation_0c1c0c0c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 568.0
  requiring ram_1.total >= 10727.0
  requiring disk_1.total >= 4082.0
  requiring network_1.throughput >= 80607.0
  providing quality = 9.0
  providing energy = ((0.82*(size^2.0))+(0.71*cpu_0.frequency))
  
 }
 contract implementation_0c1c0c0c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2540.0
  requiring ram_1.total >= 12107.0
  requiring disk_1.total >= 4374.0
  requiring network_1.throughput >= 73619.0
  providing quality = 92.0
  providing energy = ((0.07*(size^2.0))+(0.18*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0c1 {
 contract implementation_0c1c0c1i0 {
  requires component the_component_0c1c0c1c0 of type component_0c1c0c1c0
  requires component the_component_0c1c0c1c1 of type component_0c1c0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0c1c0.quality >= 49.0
  requiring the_component_0c1c0c1c1.quality >= 50.0
  requiring cpu_0.frequency >= 772.0
  requiring ram_1.total >= 6058.0
  requiring disk_1.total >= 8567.0
  requiring network_1.throughput >= 38612.0
  providing quality = 62.0
  providing energy = ((0.95*(size^2.0))+(0.02*cpu_0.frequency))
  
 }
 contract implementation_0c1c0c1i1 {
  requires component the_component_0c1c0c1c0 of type component_0c1c0c1c0
  requires component the_component_0c1c0c1c1 of type component_0c1c0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0c1c0.quality >= 20.0
  requiring the_component_0c1c0c1c1.quality >= 26.0
  requiring cpu_0.frequency >= 2799.0
  requiring ram_1.total >= 15646.0
  requiring disk_1.total >= 6049.0
  requiring network_1.throughput >= 81342.0
  providing quality = 88.0
  providing energy = ((0.04*(size^2.0))+(0.2*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0c1c0 {
 contract implementation_0c1c0c1c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2700.0
  requiring ram_1.total >= 627.0
  requiring disk_1.total >= 9409.0
  requiring network_1.throughput >= 25321.0
  providing quality = 96.0
  providing energy = ((0.88*(size^2.0))+(0.36*cpu_0.frequency))
  
 }
 contract implementation_0c1c0c1c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2452.0
  requiring ram_1.total >= 10676.0
  requiring disk_1.total >= 6701.0
  requiring network_1.throughput >= 46840.0
  providing quality = 90.0
  providing energy = ((0.73*(size^2.0))+(0.3*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0c1c1 {
 contract implementation_0c1c0c1c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2931.0
  requiring ram_1.total >= 14015.0
  requiring disk_1.total >= 10948.0
  requiring network_1.throughput >= 15267.0
  providing quality = 6.0
  providing energy = ((0.25*(size^2.0))+(0.04*cpu_0.frequency))
  
 }
 contract implementation_0c1c0c1c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1894.0
  requiring ram_1.total >= 5576.0
  requiring disk_1.total >= 15274.0
  requiring network_1.throughput >= 93735.0
  providing quality = 89.0
  providing energy = ((0.6*(size^2.0))+(0.67*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1 {
 contract implementation_0c1c1i0 {
  requires component the_component_0c1c1c0 of type component_0c1c1c0
  requires component the_component_0c1c1c1 of type component_0c1c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c1c0.quality >= 67.0
  requiring the_component_0c1c1c1.quality >= 28.0
  requiring cpu_0.frequency >= 2331.0
  requiring ram_1.total >= 15788.0
  requiring disk_1.total >= 13860.0
  requiring network_1.throughput >= 72154.0
  providing quality = 70.0
  providing energy = ((0.46*(size^2.0))+(0.17*cpu_0.frequency))
  
 }
 contract implementation_0c1c1i1 {
  requires component the_component_0c1c1c0 of type component_0c1c1c0
  requires component the_component_0c1c1c1 of type component_0c1c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c1c0.quality >= 97.0
  requiring the_component_0c1c1c1.quality >= 98.0
  requiring cpu_0.frequency >= 2851.0
  requiring ram_1.total >= 750.0
  requiring disk_1.total >= 8771.0
  requiring network_1.throughput >= 93882.0
  providing quality = 9.0
  providing energy = ((0.43*(size^2.0))+(0.74*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1c0 {
 contract implementation_0c1c1c0i0 {
  requires component the_component_0c1c1c0c0 of type component_0c1c1c0c0
  requires component the_component_0c1c1c0c1 of type component_0c1c1c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c1c0c0.quality >= 12.0
  requiring the_component_0c1c1c0c1.quality >= 81.0
  requiring cpu_0.frequency >= 675.0
  requiring ram_1.total >= 3212.0
  requiring disk_1.total >= 6203.0
  requiring network_1.throughput >= 95098.0
  providing quality = 47.0
  providing energy = ((0.59*(size^2.0))+(0.13*cpu_0.frequency))
  
 }
 contract implementation_0c1c1c0i1 {
  requires component the_component_0c1c1c0c0 of type component_0c1c1c0c0
  requires component the_component_0c1c1c0c1 of type component_0c1c1c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c1c0c0.quality >= 59.0
  requiring the_component_0c1c1c0c1.quality >= 48.0
  requiring cpu_0.frequency >= 2893.0
  requiring ram_1.total >= 12247.0
  requiring disk_1.total >= 3182.0
  requiring network_1.throughput >= 42188.0
  providing quality = 67.0
  providing energy = ((0.19*(size^2.0))+(0.29*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1c0c0 {
 contract implementation_0c1c1c0c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2204.0
  requiring ram_1.total >= 9799.0
  requiring disk_1.total >= 1742.0
  requiring network_1.throughput >= 88262.0
  providing quality = 18.0
  providing energy = ((0.7*(size^2.0))+(0.6*cpu_0.frequency))
  
 }
 contract implementation_0c1c1c0c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2356.0
  requiring ram_1.total >= 6923.0
  requiring disk_1.total >= 13518.0
  requiring network_1.throughput >= 19192.0
  providing quality = 59.0
  providing energy = ((0.5*(size^2.0))+(0.69*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1c0c1 {
 contract implementation_0c1c1c0c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2296.0
  requiring ram_1.total >= 10854.0
  requiring disk_1.total >= 9918.0
  requiring network_1.throughput >= 46338.0
  providing quality = 48.0
  providing energy = ((0.67*(size^2.0))+(0.79*cpu_0.frequency))
  
 }
 contract implementation_0c1c1c0c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 697.0
  requiring ram_1.total >= 2681.0
  requiring disk_1.total >= 11476.0
  requiring network_1.throughput >= 41922.0
  providing quality = 40.0
  providing energy = ((0.69*(size^2.0))+(0.12*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1c1 {
 contract implementation_0c1c1c1i0 {
  requires component the_component_0c1c1c1c0 of type component_0c1c1c1c0
  requires component the_component_0c1c1c1c1 of type component_0c1c1c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c1c1c0.quality >= 37.0
  requiring the_component_0c1c1c1c1.quality >= 5.0
  requiring cpu_0.frequency >= 1688.0
  requiring ram_1.total >= 1338.0
  requiring disk_1.total >= 12050.0
  requiring network_1.throughput >= 62860.0
  providing quality = 99.0
  providing energy = ((0.19*(size^2.0))+(0.15*cpu_0.frequency))
  
 }
 contract implementation_0c1c1c1i1 {
  requires component the_component_0c1c1c1c0 of type component_0c1c1c1c0
  requires component the_component_0c1c1c1c1 of type component_0c1c1c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c1c1c0.quality >= 24.0
  requiring the_component_0c1c1c1c1.quality >= 50.0
  requiring cpu_0.frequency >= 782.0
  requiring ram_1.total >= 12730.0
  requiring disk_1.total >= 12830.0
  requiring network_1.throughput >= 30835.0
  providing quality = 14.0
  providing energy = ((0.84*(size^2.0))+(0.84*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1c1c0 {
 contract implementation_0c1c1c1c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1838.0
  requiring ram_1.total >= 8597.0
  requiring disk_1.total >= 4556.0
  requiring network_1.throughput >= 48576.0
  providing quality = 59.0
  providing energy = ((0.9*(size^2.0))+(0.82*cpu_0.frequency))
  
 }
 contract implementation_0c1c1c1c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1228.0
  requiring ram_1.total >= 6798.0
  requiring disk_1.total >= 2076.0
  requiring network_1.throughput >= 19560.0
  providing quality = 58.0
  providing energy = ((0.84*(size^2.0))+(0.67*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1c1c1 {
 contract implementation_0c1c1c1c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 927.0
  requiring ram_1.total >= 3814.0
  requiring disk_1.total >= 13622.0
  requiring network_1.throughput >= 44158.0
  providing quality = 95.0
  providing energy = ((0.05*(size^2.0))+(0.37*cpu_0.frequency))
  
 }
 contract implementation_0c1c1c1c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1770.0
  requiring ram_1.total >= 5516.0
  requiring disk_1.total >= 3842.0
  requiring network_1.throughput >= 27206.0
  providing quality = 55.0
  providing energy = ((0.0*(size^2.0))+(0.03*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

request request0 for component_0 {
 meta size = 255.0
 requiring quality >= 68.0
}
minimize sum(energy)
solution {
 request0 -> implementation_0i1 {
  compute_resource_0 -> resource0 {
   cpu_0 -> cpu0_0
   ram_1 -> ram0
   disk_1 -> disk0
   network_1 -> network0
  }
  the_component_0c0 --> implementation_0c0i0 {
   compute_resource_0 -> resource1 {
    cpu_0 -> cpu1_0
    ram_1 -> ram1
    disk_1 -> disk1
    network_1 -> network1
   }
   the_component_0c0c0 --> implementation_0c0c0i1 {
    compute_resource_0 -> resource2 {
     cpu_0 -> cpu2_0
     ram_1 -> ram2
     disk_1 -> disk2
     network_1 -> network2
    }
    the_component_0c0c0c0 --> implementation_0c0c0c0i0 {
     compute_resource_0 -> resource3 {
      cpu_0 -> cpu3_0
      ram_1 -> ram3
      disk_1 -> disk3
      network_1 -> network3
     }
     
     
     
     
     
     //
     // Broken here
     //
     
     // Missing component
     
     //the_component_0c0c0c0c0 --> implementation_0c0c0c0c0i0 {
     // compute_resource_0 -> resource4 {
     //  cpu_0 -> cpu4_0
     //  ram_1 -> ram4
     //  disk_1 -> disk4
       //network_1 -> network4
     // }
     //}
     
     
     the_component_0c0c0c0c1 --> implementation_0c0c0c0c1i1 {
      compute_resource_0 -> resource5 {
       cpu_0 -> cpu5_0
       ram_1 -> ram5
       disk_1 -> disk5
       network_1 -> network5
      }
     }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    the_component_0c0c0c1 --> implementation_0c0c0c1i0 {
     compute_resource_0 -> resource6 {
      cpu_0 -> cpu6_0
      ram_1 -> ram6
      disk_1 -> disk6
      network_1 -> network6
     }
     the_component_0c0c0c1c0 --> implementation_0c0c0c1c0i1 {
      compute_resource_0 -> resource38 {
       cpu_0 -> cpu38_0
       ram_1 -> ram38
       disk_1 -> disk38
       network_1 -> network38
      }
     }
     the_component_0c0c0c1c1 --> implementation_0c0c0c1c1i0 {
      //compute_resource_0 -> resource8 {
      // cpu_0 -> cpu8_0
      // ram_1 -> ram8
      // disk_1 -> disk8
      // network_1 -> network8
      // }
     }
    }
   }
   the_component_0c0c1 --> implementation_0c0c1i0 {
    compute_resource_0 -> resource9 {
     cpu_0 -> cpu9_0
     ram_1 -> ram9
     disk_1 -> disk9
     network_1 -> network9
    }
    the_component_0c0c1c0 --> implementation_0c0c1c0i0 {
     compute_resource_0 -> resource10 {
      cpu_0 -> cpu10_0
      ram_1 -> ram10
      disk_1 -> disk10
      network_1 -> network10
     }
     the_component_0c0c1c0c0 --> implementation_0c0c1c0c0i0 {
      compute_resource_0 -> resource11 {
       cpu_0 -> cpu11_0
       ram_1 -> ram11
       disk_1 -> disk11
       network_1 -> network11
      }
     }
     the_component_0c0c1c0c1 --> implementation_0c0c1c0c1i0 {
      compute_resource_0 -> resource12 {
       cpu_0 -> cpu12_0
       ram_1 -> ram12
       disk_1 -> disk12
       network_1 -> network12
      }
     }
    }
    the_component_0c0c1c1 --> implementation_0c0c1c1i1 {
     compute_resource_0 -> resource13 {
      cpu_0 -> cpu13_0
      ram_1 -> ram13
      disk_1 -> disk13
      network_1 -> network13
     }
     the_component_0c0c1c1c0 --> implementation_0c0c1c1c0i0 {
      compute_resource_0 -> resource14 {
       cpu_0 -> cpu14_0
       ram_1 -> ram14
       disk_1 -> disk14
       network_1 -> network14
      }
     }
     the_component_0c0c1c1c1 --> implementation_0c0c1c1c1i0 {
      compute_resource_0 -> resource15 {
       cpu_0 -> cpu15_0
       ram_1 -> ram15
       disk_1 -> disk15
       network_1 -> network15
      }
     }
    }
   }
  }
  the_component_0c1 --> implementation_0c1i0 {
   compute_resource_0 -> resource16 {
    cpu_0 -> cpu16_0
    ram_1 -> ram16
    disk_1 -> disk16
    network_1 -> network16
   }
   the_component_0c1c0 --> implementation_0c1c0i1 {
    compute_resource_0 -> resource17 {
     cpu_0 -> cpu17_0
     ram_1 -> ram17
     disk_1 -> disk17
     network_1 -> network17
    }
    the_component_0c1c0c0 --> implementation_0c1c0c0i1 {
     compute_resource_0 -> resource18 {
      cpu_0 -> cpu18_0
      ram_1 -> ram18
      disk_1 -> disk18
      network_1 -> network18
     }
     the_component_0c1c0c0c0 --> implementation_0c1c0c0c0i0 {
      compute_resource_0 -> resource19 {
       cpu_0 -> cpu19_0
       ram_1 -> ram19
       disk_1 -> disk19
       network_1 -> network19
      }
     }
     the_component_0c1c0c0c1 --> implementation_0c1c0c0c1i1 {
      compute_resource_0 -> resource20 {
       cpu_0 -> cpu20_0
       ram_1 -> ram20
       disk_1 -> disk20
       network_1 -> network20
      }
     }
    }
    the_component_0c1c0c1 --> implementation_0c1c0c1i1 {
     compute_resource_0 -> resource21 {
      cpu_0 -> cpu21_0
      ram_1 -> ram21
      disk_1 -> disk21
      network_1 -> network21
     }
     the_component_0c1c0c1c0 --> implementation_0c1c0c1c0i0 {
      compute_resource_0 -> resource22 {
       cpu_0 -> cpu22_0
       ram_1 -> ram22
       disk_1 -> disk22
       network_1 -> network22
      }
     }
     the_component_0c1c0c1c1 --> implementation_0c1c0c1c1i1 {
      compute_resource_0 -> resource23 {
       cpu_0 -> cpu23_0
       ram_1 -> ram23
       disk_1 -> disk23
       network_1 -> network23
      }
     }
    }
   }
   the_component_0c1c1 --> implementation_0c1c1i0 {
    compute_resource_0 -> resource24 {
     cpu_0 -> cpu24_0
     ram_1 -> ram24
     disk_1 -> disk24
     network_1 -> network24
    }
    the_component_0c1c1c0 --> implementation_0c1c1c0i1 {
     compute_resource_0 -> resource25 {
      cpu_0 -> cpu25_0
      ram_1 -> ram25
      disk_1 -> disk25
      network_1 -> network25
     }
     the_component_0c1c1c0c0 --> implementation_0c1c1c0c0i1 {
      compute_resource_0 -> resource26 {
       cpu_0 -> cpu26_0
       ram_1 -> ram26
       disk_1 -> disk26
       network_1 -> network26
      }
     }
     the_component_0c1c1c0c1 --> implementation_0c1c1c0c1i0 {
      compute_resource_0 -> resource27 {
       cpu_0 -> cpu27_0
       ram_1 -> ram27
       disk_1 -> disk27
       network_1 -> network27
      }
     }
    }
    the_component_0c1c1c1 --> implementation_0c1c1c1i0 {
     compute_resource_0 -> resource28 {
      cpu_0 -> cpu28_0
      ram_1 -> ram28
      disk_1 -> disk28
      network_1 -> network28
     }
     the_component_0c1c1c1c0 --> implementation_0c1c1c1c0i1 {
      compute_resource_0 -> resource29 {
       cpu_0 -> cpu29_0
       ram_1 -> ram29
       disk_1 -> disk29
       network_1 -> network29
      }
     }
     the_component_0c1c1c1c1 --> implementation_0c1c1c1c1i1 {
      compute_resource_0 -> resource30 {
       cpu_0 -> cpu30_0
       ram_1 -> ram30
       disk_1 -> disk30
       network_1 -> network30
      }
     }
    }
   }
  }
 }
}
		'''	
	}
	
	static def String getValidModelWithTree(){
		
		return '''
container resource type ComputeNode {
 resource type CPU {
  property frequency [Hz]
  property load [%]
 }
 resource type RAM {
  using property total
  using property free
 }
 resource type DISK {
  using property total
  using property free
 }
 resource type NETWORK {
  property latency [ms]
  property throughput [kB/s]
 }
}
resource resource32:ComputeNode {
 resource cpu32_0:CPU {
  frequency = 2735.0
  load = 0.0
 }
 resource ram32:RAM {
  total = 9859.0
  free = 9859.0
 }
 resource disk32:DISK {
  total = 10829.0
  free = 10829.0
 }
 resource network32:NETWORK {
  latency = 168.0
  throughput = 14659.0
 }
}
resource resource0:ComputeNode {
 resource cpu0_0:CPU {
  frequency = 2476.0
  load = 0.0
 }
 resource ram0:RAM {
  total = 2179.0
  free = 2179.0
 }
 resource disk0:DISK {
  total = 2954.0
  free = 2954.0
 }
 resource network0:NETWORK {
  latency = 721.0
  throughput = 89823.0
 }
}
resource resource33:ComputeNode {
 resource cpu33_0:CPU {
  frequency = 2751.0
  load = 0.0
 }
 resource ram33:RAM {
  total = 9731.0
  free = 9731.0
 }
 resource disk33:DISK {
  total = 4216.0
  free = 4216.0
 }
 resource network33:NETWORK {
  latency = 897.0
  throughput = 83813.0
 }
}
resource resource2:ComputeNode {
 resource cpu2_0:CPU {
  frequency = 1196.0
  load = 0.0
 }
 resource ram2:RAM {
  total = 14913.0
  free = 14913.0
 }
 resource disk2:DISK {
  total = 8936.0
  free = 8936.0
 }
 resource network2:NETWORK {
  latency = 364.0
  throughput = 97895.0
 }
}
resource resource1:ComputeNode {
 resource cpu1_0:CPU {
  frequency = 2013.0
  load = 0.0
 }
 resource ram1:RAM {
  total = 4999.0
  free = 4999.0
 }
 resource disk1:DISK {
  total = 5118.0
  free = 5118.0
 }
 resource network1:NETWORK {
  latency = 87.0
  throughput = 14107.0
 }
}
resource resource42:ComputeNode {
 resource cpu42_0:CPU {
  frequency = 2722.0
  load = 0.0
 }
 resource ram42:RAM {
  total = 4763.0
  free = 4763.0
 }
 resource disk42:DISK {
  total = 7807.0
  free = 7807.0
 }
 resource network42:NETWORK {
  latency = 193.0
  throughput = 95799.0
 }
}
resource resource43:ComputeNode {
 resource cpu43_0:CPU {
  frequency = 1073.0
  load = 0.0
 }
 resource ram43:RAM {
  total = 12332.0
  free = 12332.0
 }
 resource disk43:DISK {
  total = 15114.0
  free = 15114.0
 }
 resource network43:NETWORK {
  latency = 891.0
  throughput = 65329.0
 }
}
resource resource3:ComputeNode {
 resource cpu3_0:CPU {
  frequency = 1859.0
  load = 0.0
 }
 resource ram3:RAM {
  total = 2826.0
  free = 2826.0
 }
 resource disk3:DISK {
  total = 2708.0
  free = 2708.0
 }
 resource network3:NETWORK {
  latency = 774.0
  throughput = 5415.0
 }
}
resource resource44:ComputeNode {
 resource cpu44_0:CPU {
  frequency = 1601.0
  load = 0.0
 }
 resource ram44:RAM {
  total = 1274.0
  free = 1274.0
 }
 resource disk44:DISK {
  total = 6523.0
  free = 6523.0
 }
 resource network44:NETWORK {
  latency = 400.0
  throughput = 10189.0
 }
}
resource resource36:ComputeNode {
 resource cpu36_0:CPU {
  frequency = 1322.0
  load = 0.0
 }
 resource ram36:RAM {
  total = 3911.0
  free = 3911.0
 }
 resource disk36:DISK {
  total = 11534.0
  free = 11534.0
 }
 resource network36:NETWORK {
  latency = 139.0
  throughput = 31944.0
 }
}
resource resource7:ComputeNode {
 resource cpu7_0:CPU {
  frequency = 1823.0
  load = 0.0
 }
 resource ram7:RAM {
  total = 11482.0
  free = 11482.0
 }
 resource disk7:DISK {
  total = 2318.0
  free = 2318.0
 }
 resource network7:NETWORK {
  latency = 419.0
  throughput = 60109.0
 }
}
resource resource37:ComputeNode {
 resource cpu37_0:CPU {
  frequency = 618.0
  load = 0.0
 }
 resource ram37:RAM {
  total = 12032.0
  free = 12032.0
 }
 resource disk37:DISK {
  total = 2135.0
  free = 2135.0
 }
 resource network37:NETWORK {
  latency = 407.0
  throughput = 19580.0
 }
}
resource resource38:ComputeNode {
 resource cpu38_0:CPU {
  frequency = 1375.0
  load = 0.0
 }
 resource ram38:RAM {
  total = 12312.0
  free = 12312.0
 }
 resource disk38:DISK {
  total = 12793.0
  free = 12793.0
 }
 resource network38:NETWORK {
  latency = 662.0
  throughput = 38510.0
 }
}
resource resource10:ComputeNode {
 resource cpu10_0:CPU {
  frequency = 1668.0
  load = 0.0
 }
 resource ram10:RAM {
  total = 15472.0
  free = 15472.0
 }
 resource disk10:DISK {
  total = 3555.0
  free = 3555.0
 }
 resource network10:NETWORK {
  latency = 567.0
  throughput = 51565.0
 }
}
resource resource16:ComputeNode {
 resource cpu16_0:CPU {
  frequency = 2172.0
  load = 0.0
 }
 resource ram16:RAM {
  total = 15824.0
  free = 15824.0
 }
 resource disk16:DISK {
  total = 1628.0
  free = 1628.0
 }
 resource network16:NETWORK {
  latency = 378.0
  throughput = 48407.0
 }
}
resource resource23:ComputeNode {
 resource cpu23_0:CPU {
  frequency = 1895.0
  load = 0.0
 }
 resource ram23:RAM {
  total = 5577.0
  free = 5577.0
 }
 resource disk23:DISK {
  total = 15275.0
  free = 15275.0
 }
 resource network23:NETWORK {
  latency = 595.0
  throughput = 93736.0
 }
}
resource resource17:ComputeNode {
 resource cpu17_0:CPU {
  frequency = 984.0
  load = 0.0
 }
 resource ram17:RAM {
  total = 7137.0
  free = 7137.0
 }
 resource disk17:DISK {
  total = 4485.0
  free = 4485.0
 }
 resource network17:NETWORK {
  latency = 101.0
  throughput = 69352.0
 }
}
resource resource13:ComputeNode {
 resource cpu13_0:CPU {
  frequency = 1219.0
  load = 0.0
 }
 resource ram13:RAM {
  total = 7027.0
  free = 7027.0
 }
 resource disk13:DISK {
  total = 926.0
  free = 926.0
 }
 resource network13:NETWORK {
  latency = 9.0
  throughput = 37782.0
 }
}
resource resource19:ComputeNode {
 resource cpu19_0:CPU {
  frequency = 680.0
  load = 0.0
 }
 resource ram19:RAM {
  total = 5011.0
  free = 5011.0
 }
 resource disk19:DISK {
  total = 3442.0
  free = 3442.0
 }
 resource network19:NETWORK {
  latency = 247.0
  throughput = 91479.0
 }
}
resource resource12:ComputeNode {
 resource cpu12_0:CPU {
  frequency = 2593.0
  load = 0.0
 }
 resource ram12:RAM {
  total = 9428.0
  free = 9428.0
 }
 resource disk12:DISK {
  total = 1541.0
  free = 1541.0
 }
 resource network12:NETWORK {
  latency = 146.0
  throughput = 56318.0
 }
}
resource resource45:ComputeNode {
 resource cpu45_0:CPU {
  frequency = 2760.0
  load = 0.0
 }
 resource ram45:RAM {
  total = 15146.0
  free = 15146.0
 }
 resource disk45:DISK {
  total = 3565.0
  free = 3565.0
 }
 resource network45:NETWORK {
  latency = 824.0
  throughput = 47221.0
 }
}
resource resource5:ComputeNode {
 resource cpu5_0:CPU {
  frequency = 2193.0
  load = 0.0
 }
 resource ram5:RAM {
  total = 7449.0
  free = 7449.0
 }
 resource disk5:DISK {
  total = 15947.0
  free = 15947.0
 }
 resource network5:NETWORK {
  latency = 101.0
  throughput = 6182.0
 }
}
resource resource25:ComputeNode {
 resource cpu25_0:CPU {
  frequency = 2894.0
  load = 0.0
 }
 resource ram25:RAM {
  total = 12248.0
  free = 12248.0
 }
 resource disk25:DISK {
  total = 3183.0
  free = 3183.0
 }
 resource network25:NETWORK {
  latency = 164.0
  throughput = 42189.0
 }
}
resource resource24:ComputeNode {
 resource cpu24_0:CPU {
  frequency = 2332.0
  load = 0.0
 }
 resource ram24:RAM {
  total = 15789.0
  free = 15789.0
 }
 resource disk24:DISK {
  total = 13861.0
  free = 13861.0
 }
 resource network24:NETWORK {
  latency = 877.0
  throughput = 72155.0
 }
}
resource resource26:ComputeNode {
 resource cpu26_0:CPU {
  frequency = 2357.0
  load = 0.0
 }
 resource ram26:RAM {
  total = 6924.0
  free = 6924.0
 }
 resource disk26:DISK {
  total = 13519.0
  free = 13519.0
 }
 resource network26:NETWORK {
  latency = 332.0
  throughput = 19193.0
 }
}
resource resource28:ComputeNode {
 resource cpu28_0:CPU {
  frequency = 1689.0
  load = 0.0
 }
 resource ram28:RAM {
  total = 1339.0
  free = 1339.0
 }
 resource disk28:DISK {
  total = 12051.0
  free = 12051.0
 }
 resource network28:NETWORK {
  latency = 32.0
  throughput = 62861.0
 }
}
resource resource46:ComputeNode {
 resource cpu46_0:CPU {
  frequency = 904.0
  load = 0.0
 }
 resource ram46:RAM {
  total = 14153.0
  free = 14153.0
 }
 resource disk46:DISK {
  total = 6637.0
  free = 6637.0
 }
 resource network46:NETWORK {
  latency = 1.0
  throughput = 62367.0
 }
}
resource resource22:ComputeNode {
 resource cpu22_0:CPU {
  frequency = 2701.0
  load = 0.0
 }
 resource ram22:RAM {
  total = 628.0
  free = 628.0
 }
 resource disk22:DISK {
  total = 9410.0
  free = 9410.0
 }
 resource network22:NETWORK {
  latency = 138.0
  throughput = 25322.0
 }
}
resource resource29:ComputeNode {
 resource cpu29_0:CPU {
  frequency = 1229.0
  load = 0.0
 }
 resource ram29:RAM {
  total = 6799.0
  free = 6799.0
 }
 resource disk29:DISK {
  total = 2077.0
  free = 2077.0
 }
 resource network29:NETWORK {
  latency = 481.0
  throughput = 19561.0
 }
}
resource resource21:ComputeNode {
 resource cpu21_0:CPU {
  frequency = 2800.0
  load = 0.0
 }
 resource ram21:RAM {
  total = 15647.0
  free = 15647.0
 }
 resource disk21:DISK {
  total = 6050.0
  free = 6050.0
 }
 resource network21:NETWORK {
  latency = 677.0
  throughput = 81343.0
 }
}
resource resource31:ComputeNode {
 resource cpu31_0:CPU {
  frequency = 647.0
  load = 0.0
 }
 resource ram31:RAM {
  total = 14070.0
  free = 14070.0
 }
 resource disk31:DISK {
  total = 990.0
  free = 990.0
 }
 resource network31:NETWORK {
  latency = 894.0
  throughput = 33554.0
 }
}
resource resource8:ComputeNode {
 resource cpu8_0:CPU {
  frequency = 1914.0
  load = 0.0
 }
 resource ram8:RAM {
  total = 11646.0
  free = 11646.0
 }
 resource disk8:DISK {
  total = 14083.0
  free = 14083.0
 }
 resource network8:NETWORK {
  latency = 880.0
  throughput = 25447.0
 }
}
resource resource40:ComputeNode {
 resource cpu40_0:CPU {
  frequency = 2641.0
  load = 0.0
 }
 resource ram40:RAM {
  total = 15943.0
  free = 15943.0
 }
 resource disk40:DISK {
  total = 9536.0
  free = 9536.0
 }
 resource network40:NETWORK {
  latency = 637.0
  throughput = 36711.0
 }
}
resource resource27:ComputeNode {
 resource cpu27_0:CPU {
  frequency = 2297.0
  load = 0.0
 }
 resource ram27:RAM {
  total = 10855.0
  free = 10855.0
 }
 resource disk27:DISK {
  total = 9919.0
  free = 9919.0
 }
 resource network27:NETWORK {
  latency = 792.0
  throughput = 46339.0
 }
}
resource resource20:ComputeNode {
 resource cpu20_0:CPU {
  frequency = 2541.0
  load = 0.0
 }
 resource ram20:RAM {
  total = 12108.0
  free = 12108.0
 }
 resource disk20:DISK {
  total = 4375.0
  free = 4375.0
 }
 resource network20:NETWORK {
  latency = 214.0
  throughput = 73620.0
 }
}
resource resource18:ComputeNode {
 resource cpu18_0:CPU {
  frequency = 2518.0
  load = 0.0
 }
 resource ram18:RAM {
  total = 12873.0
  free = 12873.0
 }
 resource disk18:DISK {
  total = 3266.0
  free = 3266.0
 }
 resource network18:NETWORK {
  latency = 864.0
  throughput = 57617.0
 }
}
resource resource15:ComputeNode {
 resource cpu15_0:CPU {
  frequency = 642.0
  load = 0.0
 }
 resource ram15:RAM {
  total = 4805.0
  free = 4805.0
 }
 resource disk15:DISK {
  total = 13593.0
  free = 13593.0
 }
 resource network15:NETWORK {
  latency = 180.0
  throughput = 68836.0
 }
}
resource resource30:ComputeNode {
 resource cpu30_0:CPU {
  frequency = 1771.0
  load = 0.0
 }
 resource ram30:RAM {
  total = 5517.0
  free = 5517.0
 }
 resource disk30:DISK {
  total = 3843.0
  free = 3843.0
 }
 resource network30:NETWORK {
  latency = 948.0
  throughput = 27207.0
 }
}
resource resource11:ComputeNode {
 resource cpu11_0:CPU {
  frequency = 2032.0
  load = 0.0
 }
 resource ram11:RAM {
  total = 3519.0
  free = 3519.0
 }
 resource disk11:DISK {
  total = 12100.0
  free = 12100.0
 }
 resource network11:NETWORK {
  latency = 545.0
  throughput = 29489.0
 }
}
resource resource14:ComputeNode {
 resource cpu14_0:CPU {
  frequency = 1302.0
  load = 0.0
 }
 resource ram14:RAM {
  total = 6760.0
  free = 6760.0
 }
 resource disk14:DISK {
  total = 6600.0
  free = 6600.0
 }
 resource network14:NETWORK {
  latency = 667.0
  throughput = 44543.0
 }
}
resource resource41:ComputeNode {
 resource cpu41_0:CPU {
  frequency = 2194.0
  load = 0.0
 }
 resource ram41:RAM {
  total = 691.0
  free = 691.0
 }
 resource disk41:DISK {
  total = 1693.0
  free = 1693.0
 }
 resource network41:NETWORK {
  latency = 457.0
  throughput = 36288.0
 }
}
resource resource9:ComputeNode {
 resource cpu9_0:CPU {
  frequency = 2830.0
  load = 0.0
 }
 resource ram9:RAM {
  total = 4180.0
  free = 4180.0
 }
 resource disk9:DISK {
  total = 15334.0
  free = 15334.0
 }
 resource network9:NETWORK {
  latency = 718.0
  throughput = 88832.0
 }
}
resource resource6:ComputeNode {
 resource cpu6_0:CPU {
  frequency = 2671.0
  load = 0.0
 }
 resource ram6:RAM {
  total = 15864.0
  free = 15864.0
 }
 resource disk6:DISK {
  total = 9372.0
  free = 9372.0
 }
 resource network6:NETWORK {
  latency = 381.0
  throughput = 65735.0
 }
}
resource resource39:ComputeNode {
 resource cpu39_0:CPU {
  frequency = 1931.0
  load = 0.0
 }
 resource ram39:RAM {
  total = 7843.0
  free = 7843.0
 }
 resource disk39:DISK {
  total = 6906.0
  free = 6906.0
 }
 resource network39:NETWORK {
  latency = 697.0
  throughput = 69742.0
 }
}
resource resource4:ComputeNode {
 resource cpu4_0:CPU {
  frequency = 1716.0
  load = 0.0
 }
 resource ram4:RAM {
  total = 8263.0
  free = 8263.0
 }
 resource disk4:DISK {
  total = 8707.0
  free = 8707.0
 }
 resource network4:NETWORK {
  latency = 984.0
  throughput = 33656.0
 }
}
resource resource34:ComputeNode {
 resource cpu34_0:CPU {
  frequency = 1055.0
  load = 0.0
 }
 resource ram34:RAM {
  total = 8702.0
  free = 8702.0
 }
 resource disk34:DISK {
  total = 1718.0
  free = 1718.0
 }
 resource network34:NETWORK {
  latency = 692.0
  throughput = 95415.0
 }
}
resource resource35:ComputeNode {
 resource cpu35_0:CPU {
  frequency = 1009.0
  load = 0.0
 }
 resource ram35:RAM {
  total = 10404.0
  free = 10404.0
 }
 resource disk35:DISK {
  total = 9811.0
  free = 9811.0
 }
 resource network35:NETWORK {
  latency = 610.0
  throughput = 64980.0
 }
}
property total [MB]
property free [MB]
meta size
property energy [J]
property quality [%]
component component_0 {
 contract implementation_0i0 {
  requires component the_component_0c0 of type component_0c0
  requires component the_component_0c1 of type component_0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0.quality >= 1.0
  requiring the_component_0c1.quality >= 13.0
  requiring cpu_0.frequency >= 2605.0
  requiring ram_1.total >= 6109.0
  requiring disk_1.total >= 13297.0
  requiring network_1.throughput >= 59432.0
  providing quality = 56.0
  providing energy = ((0.02*(size^2.0))+(0.62*cpu_0.frequency))
  
 }
 contract implementation_0i1 {
  requires component the_component_0c0 of type component_0c0
  requires component the_component_0c1 of type component_0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0.quality >= 68.0
  requiring the_component_0c1.quality >= 21.0
  requiring cpu_0.frequency >= 2475.0
  requiring ram_1.total >= 2178.0
  requiring disk_1.total >= 2953.0
  requiring network_1.throughput >= 89822.0
  providing quality = 68.0
  providing energy = ((0.52*(size^2.0))+(0.01*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0 {
 contract implementation_0c0i0 {
  requires component the_component_0c0c0 of type component_0c0c0
  requires component the_component_0c0c1 of type component_0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0.quality >= 35.0
  requiring the_component_0c0c1.quality >= 24.0
  requiring cpu_0.frequency >= 2012.0
  requiring ram_1.total >= 4998.0
  requiring disk_1.total >= 5117.0
  requiring network_1.throughput >= 14106.0
  providing quality = 68.0
  providing energy = ((0.59*(size^2.0))+(0.02*cpu_0.frequency))
  
 }
 contract implementation_0c0i1 {
  requires component the_component_0c0c0 of type component_0c0c0
  requires component the_component_0c0c1 of type component_0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0.quality >= 84.0
  requiring the_component_0c0c1.quality >= 2.0
  requiring cpu_0.frequency >= 1578.0
  requiring ram_1.total >= 2567.0
  requiring disk_1.total >= 1771.0
  requiring network_1.throughput >= 6541.0
  providing quality = 61.0
  providing energy = ((0.59*(size^2.0))+(0.94*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0 {
 contract implementation_0c0c0i0 {
  requires component the_component_0c0c0c0 of type component_0c0c0c0
  requires component the_component_0c0c0c1 of type component_0c0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0c0.quality >= 31.0
  requiring the_component_0c0c0c1.quality >= 41.0
  requiring cpu_0.frequency >= 2570.0
  requiring ram_1.total >= 3737.0
  requiring disk_1.total >= 7988.0
  requiring network_1.throughput >= 83381.0
  providing quality = 14.0
  providing energy = ((0.9*(size^2.0))+(0.29*cpu_0.frequency))
  
 }
 contract implementation_0c0c0i1 {
  requires component the_component_0c0c0c0 of type component_0c0c0c0
  requires component the_component_0c0c0c1 of type component_0c0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0c0.quality >= 75.0
  requiring the_component_0c0c0c1.quality >= 82.0
  requiring cpu_0.frequency >= 1195.0
  requiring ram_1.total >= 14912.0
  requiring disk_1.total >= 8935.0
  requiring network_1.throughput >= 97894.0
  providing quality = 72.0
  providing energy = ((0.5*(size^2.0))+(0.99*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0c0 {
 contract implementation_0c0c0c0i0 {
  requires component the_component_0c0c0c0c0 of type component_0c0c0c0c0
  requires component the_component_0c0c0c0c1 of type component_0c0c0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0c0c0.quality >= 89.0
  requiring the_component_0c0c0c0c1.quality >= 71.0
  requiring cpu_0.frequency >= 1858.0
  requiring ram_1.total >= 2825.0
  requiring disk_1.total >= 2707.0
  requiring network_1.throughput >= 5414.0
  providing quality = 75.0
  providing energy = ((0.87*(size^2.0))+(0.98*cpu_0.frequency))
  
 }
 contract implementation_0c0c0c0i1 {
  requires component the_component_0c0c0c0c0 of type component_0c0c0c0c0
  requires component the_component_0c0c0c0c1 of type component_0c0c0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0c0c0.quality >= 45.0
  requiring the_component_0c0c0c0c1.quality >= 67.0
  requiring cpu_0.frequency >= 1566.0
  requiring ram_1.total >= 8601.0
  requiring disk_1.total >= 15770.0
  requiring network_1.throughput >= 80225.0
  providing quality = 66.0
  providing energy = ((0.9*(size^2.0))+(0.13*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0c0c0 {
 contract implementation_0c0c0c0c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1715.0
  requiring ram_1.total >= 8262.0
  requiring disk_1.total >= 8706.0
  requiring network_1.throughput >= 33655.0
  providing quality = 89.0
  providing energy = ((0.31*(size^2.0))+(0.12*cpu_0.frequency))
  
 }
 contract implementation_0c0c0c0c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2290.0
  requiring ram_1.total >= 8914.0
  requiring disk_1.total >= 3658.0
  requiring network_1.throughput >= 61931.0
  providing quality = 65.0
  providing energy = ((0.98*(size^2.0))+(0.88*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0c0c1 {
 contract implementation_0c0c0c0c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2038.0
  requiring ram_1.total >= 8941.0
  requiring disk_1.total >= 11052.0
  requiring network_1.throughput >= 85378.0
  providing quality = 86.0
  providing energy = ((0.37*(size^2.0))+(0.71*cpu_0.frequency))
  
 }
 contract implementation_0c0c0c0c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2192.0
  requiring ram_1.total >= 7448.0
  requiring disk_1.total >= 15946.0
  requiring network_1.throughput >= 6181.0
  providing quality = 89.0
  providing energy = ((0.1*(size^2.0))+(0.63*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0c1 {
 contract implementation_0c0c0c1i0 {
  requires component the_component_0c0c0c1c0 of type component_0c0c0c1c0
  requires component the_component_0c0c0c1c1 of type component_0c0c0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0c1c0.quality >= 34.0
  requiring the_component_0c0c0c1c1.quality >= 45.0
  requiring cpu_0.frequency >= 2670.0
  requiring ram_1.total >= 15863.0
  requiring disk_1.total >= 9371.0
  requiring network_1.throughput >= 65734.0
  providing quality = 82.0
  providing energy = ((0.05*(size^2.0))+(0.4*cpu_0.frequency))
  
 }
 contract implementation_0c0c0c1i1 {
  requires component the_component_0c0c0c1c0 of type component_0c0c0c1c0
  requires component the_component_0c0c0c1c1 of type component_0c0c0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c0c1c0.quality >= 35.0
  requiring the_component_0c0c0c1c1.quality >= 35.0
  requiring cpu_0.frequency >= 2216.0
  requiring ram_1.total >= 5576.0
  requiring disk_1.total >= 2226.0
  requiring network_1.throughput >= 66764.0
  providing quality = 90.0
  providing energy = ((0.79*(size^2.0))+(0.99*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0c1c0 {
 contract implementation_0c0c0c1c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1822.0
  requiring ram_1.total >= 11481.0
  requiring disk_1.total >= 2317.0
  requiring network_1.throughput >= 60108.0
  providing quality = 94.0
  providing energy = ((0.67*(size^2.0))+(0.06*cpu_0.frequency))
  
 }
 contract implementation_0c0c0c1c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1321.0
  requiring ram_1.total >= 7221.0
  requiring disk_1.total >= 12125.0
  requiring network_1.throughput >= 33612.0
  providing quality = 79.0
  providing energy = ((0.07*(size^2.0))+(0.35*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c0c1c1 {
 contract implementation_0c0c0c1c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1913.0
  requiring ram_1.total >= 11645.0
  requiring disk_1.total >= 14082.0
  requiring network_1.throughput >= 25446.0
  providing quality = 45.0
  providing energy = ((0.74*(size^2.0))+(0.53*cpu_0.frequency))
  
 }
 contract implementation_0c0c0c1c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1561.0
  requiring ram_1.total >= 11493.0
  requiring disk_1.total >= 14106.0
  requiring network_1.throughput >= 70959.0
  providing quality = 27.0
  providing energy = ((0.85*(size^2.0))+(0.01*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1 {
 contract implementation_0c0c1i0 {
  requires component the_component_0c0c1c0 of type component_0c0c1c0
  requires component the_component_0c0c1c1 of type component_0c0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c1c0.quality >= 46.0
  requiring the_component_0c0c1c1.quality >= 73.0
  requiring cpu_0.frequency >= 2829.0
  requiring ram_1.total >= 4179.0
  requiring disk_1.total >= 15333.0
  requiring network_1.throughput >= 88831.0
  providing quality = 24.0
  providing energy = ((0.68*(size^2.0))+(0.3*cpu_0.frequency))
  
 }
 contract implementation_0c0c1i1 {
  requires component the_component_0c0c1c0 of type component_0c0c1c0
  requires component the_component_0c0c1c1 of type component_0c0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c1c0.quality >= 36.0
  requiring the_component_0c0c1c1.quality >= 6.0
  requiring cpu_0.frequency >= 575.0
  requiring ram_1.total >= 5309.0
  requiring disk_1.total >= 2210.0
  requiring network_1.throughput >= 87023.0
  providing quality = 14.0
  providing energy = ((0.75*(size^2.0))+(0.19*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1c0 {
 contract implementation_0c0c1c0i0 {
  requires component the_component_0c0c1c0c0 of type component_0c0c1c0c0
  requires component the_component_0c0c1c0c1 of type component_0c0c1c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c1c0c0.quality >= 89.0
  requiring the_component_0c0c1c0c1.quality >= 27.0
  requiring cpu_0.frequency >= 1667.0
  requiring ram_1.total >= 15471.0
  requiring disk_1.total >= 3554.0
  requiring network_1.throughput >= 51564.0
  providing quality = 53.0
  providing energy = ((0.31*(size^2.0))+(0.94*cpu_0.frequency))
  
 }
 contract implementation_0c0c1c0i1 {
  requires component the_component_0c0c1c0c0 of type component_0c0c1c0c0
  requires component the_component_0c0c1c0c1 of type component_0c0c1c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c1c0c0.quality >= 77.0
  requiring the_component_0c0c1c0c1.quality >= 58.0
  requiring cpu_0.frequency >= 2010.0
  requiring ram_1.total >= 6979.0
  requiring disk_1.total >= 9786.0
  requiring network_1.throughput >= 51477.0
  providing quality = 95.0
  providing energy = ((0.65*(size^2.0))+(0.5*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1c0c0 {
 contract implementation_0c0c1c0c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2031.0
  requiring ram_1.total >= 3518.0
  requiring disk_1.total >= 12099.0
  requiring network_1.throughput >= 29488.0
  providing quality = 95.0
  providing energy = ((0.79*(size^2.0))+(0.0*cpu_0.frequency))
  
 }
 contract implementation_0c0c1c0c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1675.0
  requiring ram_1.total >= 14329.0
  requiring disk_1.total >= 13348.0
  requiring network_1.throughput >= 34862.0
  providing quality = 3.0
  providing energy = ((0.99*(size^2.0))+(0.77*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1c0c1 {
 contract implementation_0c0c1c0c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2592.0
  requiring ram_1.total >= 9427.0
  requiring disk_1.total >= 1540.0
  requiring network_1.throughput >= 56317.0
  providing quality = 68.0
  providing energy = ((0.74*(size^2.0))+(0.73*cpu_0.frequency))
  
 }
 contract implementation_0c0c1c0c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2645.0
  requiring ram_1.total >= 11224.0
  requiring disk_1.total >= 15330.0
  requiring network_1.throughput >= 61842.0
  providing quality = 4.0
  providing energy = ((0.09*(size^2.0))+(0.85*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1c1 {
 contract implementation_0c0c1c1i0 {
  requires component the_component_0c0c1c1c0 of type component_0c0c1c1c0
  requires component the_component_0c0c1c1c1 of type component_0c0c1c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c1c1c0.quality >= 94.0
  requiring the_component_0c0c1c1c1.quality >= 82.0
  requiring cpu_0.frequency >= 1039.0
  requiring ram_1.total >= 14989.0
  requiring disk_1.total >= 7301.0
  requiring network_1.throughput >= 15856.0
  providing quality = 100.0
  providing energy = ((0.17*(size^2.0))+(0.35*cpu_0.frequency))
  
 }
 contract implementation_0c0c1c1i1 {
  requires component the_component_0c0c1c1c0 of type component_0c0c1c1c0
  requires component the_component_0c0c1c1c1 of type component_0c0c1c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c0c1c1c0.quality >= 2.0
  requiring the_component_0c0c1c1c1.quality >= 46.0
  requiring cpu_0.frequency >= 1218.0
  requiring ram_1.total >= 7026.0
  requiring disk_1.total >= 925.0
  requiring network_1.throughput >= 37781.0
  providing quality = 73.0
  providing energy = ((0.31*(size^2.0))+(0.54*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1c1c0 {
 contract implementation_0c0c1c1c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1301.0
  requiring ram_1.total >= 6759.0
  requiring disk_1.total >= 6599.0
  requiring network_1.throughput >= 44542.0
  providing quality = 75.0
  providing energy = ((0.38*(size^2.0))+(0.55*cpu_0.frequency))
  
 }
 contract implementation_0c0c1c1c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 857.0
  requiring ram_1.total >= 11845.0
  requiring disk_1.total >= 8196.0
  requiring network_1.throughput >= 30833.0
  providing quality = 65.0
  providing energy = ((0.7*(size^2.0))+(0.75*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c0c1c1c1 {
 contract implementation_0c0c1c1c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 641.0
  requiring ram_1.total >= 4804.0
  requiring disk_1.total >= 13592.0
  requiring network_1.throughput >= 68835.0
  providing quality = 56.0
  providing energy = ((0.1*(size^2.0))+(0.34*cpu_0.frequency))
  
 }
 contract implementation_0c0c1c1c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1565.0
  requiring ram_1.total >= 3190.0
  requiring disk_1.total >= 587.0
  requiring network_1.throughput >= 10681.0
  providing quality = 75.0
  providing energy = ((0.89*(size^2.0))+(0.3*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1 {
 contract implementation_0c1i0 {
  requires component the_component_0c1c0 of type component_0c1c0
  requires component the_component_0c1c1 of type component_0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0.quality >= 35.0
  requiring the_component_0c1c1.quality >= 44.0
  requiring cpu_0.frequency >= 2171.0
  requiring ram_1.total >= 15823.0
  requiring disk_1.total >= 1627.0
  requiring network_1.throughput >= 48406.0
  providing quality = 46.0
  providing energy = ((0.76*(size^2.0))+(0.77*cpu_0.frequency))
  
 }
 contract implementation_0c1i1 {
  requires component the_component_0c1c0 of type component_0c1c0
  requires component the_component_0c1c1 of type component_0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0.quality >= 88.0
  requiring the_component_0c1c1.quality >= 64.0
  requiring cpu_0.frequency >= 2724.0
  requiring ram_1.total >= 6670.0
  requiring disk_1.total >= 13598.0
  requiring network_1.throughput >= 79810.0
  providing quality = 78.0
  providing energy = ((0.0*(size^2.0))+(0.27*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0 {
 contract implementation_0c1c0i0 {
  requires component the_component_0c1c0c0 of type component_0c1c0c0
  requires component the_component_0c1c0c1 of type component_0c1c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0c0.quality >= 47.0
  requiring the_component_0c1c0c1.quality >= 44.0
  requiring cpu_0.frequency >= 893.0
  requiring ram_1.total >= 6752.0
  requiring disk_1.total >= 6691.0
  requiring network_1.throughput >= 19933.0
  providing quality = 14.0
  providing energy = ((0.41*(size^2.0))+(0.05*cpu_0.frequency))
  
 }
 contract implementation_0c1c0i1 {
  requires component the_component_0c1c0c0 of type component_0c1c0c0
  requires component the_component_0c1c0c1 of type component_0c1c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0c0.quality >= 8.0
  requiring the_component_0c1c0c1.quality >= 88.0
  requiring cpu_0.frequency >= 983.0
  requiring ram_1.total >= 7136.0
  requiring disk_1.total >= 4484.0
  requiring network_1.throughput >= 69351.0
  providing quality = 35.0
  providing energy = ((0.83*(size^2.0))+(0.54*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0c0 {
 contract implementation_0c1c0c0i0 {
  requires component the_component_0c1c0c0c0 of type component_0c1c0c0c0
  requires component the_component_0c1c0c0c1 of type component_0c1c0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0c0c0.quality >= 70.0
  requiring the_component_0c1c0c0c1.quality >= 43.0
  requiring cpu_0.frequency >= 868.0
  requiring ram_1.total >= 12108.0
  requiring disk_1.total >= 13067.0
  requiring network_1.throughput >= 93140.0
  providing quality = 68.0
  providing energy = ((0.04*(size^2.0))+(0.59*cpu_0.frequency))
  
 }
 contract implementation_0c1c0c0i1 {
  requires component the_component_0c1c0c0c0 of type component_0c1c0c0c0
  requires component the_component_0c1c0c0c1 of type component_0c1c0c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0c0c0.quality >= 27.0
  requiring the_component_0c1c0c0c1.quality >= 92.0
  requiring cpu_0.frequency >= 2517.0
  requiring ram_1.total >= 12872.0
  requiring disk_1.total >= 3265.0
  requiring network_1.throughput >= 57616.0
  providing quality = 8.0
  providing energy = ((0.91*(size^2.0))+(0.02*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0c0c0 {
 contract implementation_0c1c0c0c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 679.0
  requiring ram_1.total >= 5010.0
  requiring disk_1.total >= 3441.0
  requiring network_1.throughput >= 91478.0
  providing quality = 77.0
  providing energy = ((0.52*(size^2.0))+(0.2*cpu_0.frequency))
  
 }
 contract implementation_0c1c0c0c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2611.0
  requiring ram_1.total >= 5126.0
  requiring disk_1.total >= 14183.0
  requiring network_1.throughput >= 62309.0
  providing quality = 49.0
  providing energy = ((0.19*(size^2.0))+(0.3*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0c0c1 {
 contract implementation_0c1c0c0c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 568.0
  requiring ram_1.total >= 10727.0
  requiring disk_1.total >= 4082.0
  requiring network_1.throughput >= 80607.0
  providing quality = 9.0
  providing energy = ((0.82*(size^2.0))+(0.71*cpu_0.frequency))
  
 }
 contract implementation_0c1c0c0c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2540.0
  requiring ram_1.total >= 12107.0
  requiring disk_1.total >= 4374.0
  requiring network_1.throughput >= 73619.0
  providing quality = 92.0
  providing energy = ((0.07*(size^2.0))+(0.18*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0c1 {
 contract implementation_0c1c0c1i0 {
  requires component the_component_0c1c0c1c0 of type component_0c1c0c1c0
  requires component the_component_0c1c0c1c1 of type component_0c1c0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0c1c0.quality >= 49.0
  requiring the_component_0c1c0c1c1.quality >= 50.0
  requiring cpu_0.frequency >= 772.0
  requiring ram_1.total >= 6058.0
  requiring disk_1.total >= 8567.0
  requiring network_1.throughput >= 38612.0
  providing quality = 62.0
  providing energy = ((0.95*(size^2.0))+(0.02*cpu_0.frequency))
  
 }
 contract implementation_0c1c0c1i1 {
  requires component the_component_0c1c0c1c0 of type component_0c1c0c1c0
  requires component the_component_0c1c0c1c1 of type component_0c1c0c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c0c1c0.quality >= 20.0
  requiring the_component_0c1c0c1c1.quality >= 26.0
  requiring cpu_0.frequency >= 2799.0
  requiring ram_1.total >= 15646.0
  requiring disk_1.total >= 6049.0
  requiring network_1.throughput >= 81342.0
  providing quality = 88.0
  providing energy = ((0.04*(size^2.0))+(0.2*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0c1c0 {
 contract implementation_0c1c0c1c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2700.0
  requiring ram_1.total >= 627.0
  requiring disk_1.total >= 9409.0
  requiring network_1.throughput >= 25321.0
  providing quality = 96.0
  providing energy = ((0.88*(size^2.0))+(0.36*cpu_0.frequency))
  
 }
 contract implementation_0c1c0c1c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2452.0
  requiring ram_1.total >= 10676.0
  requiring disk_1.total >= 6701.0
  requiring network_1.throughput >= 46840.0
  providing quality = 90.0
  providing energy = ((0.73*(size^2.0))+(0.3*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c0c1c1 {
 contract implementation_0c1c0c1c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2931.0
  requiring ram_1.total >= 14015.0
  requiring disk_1.total >= 10948.0
  requiring network_1.throughput >= 15267.0
  providing quality = 6.0
  providing energy = ((0.25*(size^2.0))+(0.04*cpu_0.frequency))
  
 }
 contract implementation_0c1c0c1c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1894.0
  requiring ram_1.total >= 5576.0
  requiring disk_1.total >= 15274.0
  requiring network_1.throughput >= 93735.0
  providing quality = 89.0
  providing energy = ((0.6*(size^2.0))+(0.67*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1 {
 contract implementation_0c1c1i0 {
  requires component the_component_0c1c1c0 of type component_0c1c1c0
  requires component the_component_0c1c1c1 of type component_0c1c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c1c0.quality >= 67.0
  requiring the_component_0c1c1c1.quality >= 28.0
  requiring cpu_0.frequency >= 2331.0
  requiring ram_1.total >= 15788.0
  requiring disk_1.total >= 13860.0
  requiring network_1.throughput >= 72154.0
  providing quality = 70.0
  providing energy = ((0.46*(size^2.0))+(0.17*cpu_0.frequency))
  
 }
 contract implementation_0c1c1i1 {
  requires component the_component_0c1c1c0 of type component_0c1c1c0
  requires component the_component_0c1c1c1 of type component_0c1c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c1c0.quality >= 97.0
  requiring the_component_0c1c1c1.quality >= 98.0
  requiring cpu_0.frequency >= 2851.0
  requiring ram_1.total >= 750.0
  requiring disk_1.total >= 8771.0
  requiring network_1.throughput >= 93882.0
  providing quality = 9.0
  providing energy = ((0.43*(size^2.0))+(0.74*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1c0 {
 contract implementation_0c1c1c0i0 {
  requires component the_component_0c1c1c0c0 of type component_0c1c1c0c0
  requires component the_component_0c1c1c0c1 of type component_0c1c1c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c1c0c0.quality >= 12.0
  requiring the_component_0c1c1c0c1.quality >= 81.0
  requiring cpu_0.frequency >= 675.0
  requiring ram_1.total >= 3212.0
  requiring disk_1.total >= 6203.0
  requiring network_1.throughput >= 95098.0
  providing quality = 47.0
  providing energy = ((0.59*(size^2.0))+(0.13*cpu_0.frequency))
  
 }
 contract implementation_0c1c1c0i1 {
  requires component the_component_0c1c1c0c0 of type component_0c1c1c0c0
  requires component the_component_0c1c1c0c1 of type component_0c1c1c0c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c1c0c0.quality >= 59.0
  requiring the_component_0c1c1c0c1.quality >= 48.0
  requiring cpu_0.frequency >= 2893.0
  requiring ram_1.total >= 12247.0
  requiring disk_1.total >= 3182.0
  requiring network_1.throughput >= 42188.0
  providing quality = 67.0
  providing energy = ((0.19*(size^2.0))+(0.29*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1c0c0 {
 contract implementation_0c1c1c0c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2204.0
  requiring ram_1.total >= 9799.0
  requiring disk_1.total >= 1742.0
  requiring network_1.throughput >= 88262.0
  providing quality = 18.0
  providing energy = ((0.7*(size^2.0))+(0.6*cpu_0.frequency))
  
 }
 contract implementation_0c1c1c0c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2356.0
  requiring ram_1.total >= 6923.0
  requiring disk_1.total >= 13518.0
  requiring network_1.throughput >= 19192.0
  providing quality = 59.0
  providing energy = ((0.5*(size^2.0))+(0.69*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1c0c1 {
 contract implementation_0c1c1c0c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 2296.0
  requiring ram_1.total >= 10854.0
  requiring disk_1.total >= 9918.0
  requiring network_1.throughput >= 46338.0
  providing quality = 48.0
  providing energy = ((0.67*(size^2.0))+(0.79*cpu_0.frequency))
  
 }
 contract implementation_0c1c1c0c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 697.0
  requiring ram_1.total >= 2681.0
  requiring disk_1.total >= 11476.0
  requiring network_1.throughput >= 41922.0
  providing quality = 40.0
  providing energy = ((0.69*(size^2.0))+(0.12*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1c1 {
 contract implementation_0c1c1c1i0 {
  requires component the_component_0c1c1c1c0 of type component_0c1c1c1c0
  requires component the_component_0c1c1c1c1 of type component_0c1c1c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c1c1c0.quality >= 37.0
  requiring the_component_0c1c1c1c1.quality >= 5.0
  requiring cpu_0.frequency >= 1688.0
  requiring ram_1.total >= 1338.0
  requiring disk_1.total >= 12050.0
  requiring network_1.throughput >= 62860.0
  providing quality = 99.0
  providing energy = ((0.19*(size^2.0))+(0.15*cpu_0.frequency))
  
 }
 contract implementation_0c1c1c1i1 {
  requires component the_component_0c1c1c1c0 of type component_0c1c1c1c0
  requires component the_component_0c1c1c1c1 of type component_0c1c1c1c1
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring the_component_0c1c1c1c0.quality >= 24.0
  requiring the_component_0c1c1c1c1.quality >= 50.0
  requiring cpu_0.frequency >= 782.0
  requiring ram_1.total >= 12730.0
  requiring disk_1.total >= 12830.0
  requiring network_1.throughput >= 30835.0
  providing quality = 14.0
  providing energy = ((0.84*(size^2.0))+(0.84*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1c1c0 {
 contract implementation_0c1c1c1c0i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1838.0
  requiring ram_1.total >= 8597.0
  requiring disk_1.total >= 4556.0
  requiring network_1.throughput >= 48576.0
  providing quality = 59.0
  providing energy = ((0.9*(size^2.0))+(0.82*cpu_0.frequency))
  
 }
 contract implementation_0c1c1c1c0i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1228.0
  requiring ram_1.total >= 6798.0
  requiring disk_1.total >= 2076.0
  requiring network_1.throughput >= 19560.0
  providing quality = 58.0
  providing energy = ((0.84*(size^2.0))+(0.67*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

component component_0c1c1c1c1 {
 contract implementation_0c1c1c1c1i0 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 927.0
  requiring ram_1.total >= 3814.0
  requiring disk_1.total >= 13622.0
  requiring network_1.throughput >= 44158.0
  providing quality = 95.0
  providing energy = ((0.05*(size^2.0))+(0.37*cpu_0.frequency))
  
 }
 contract implementation_0c1c1c1c1i1 {
  requires resource compute_resource_0 of type ComputeNode with {
   cpu_0 of type CPU
   ram_1 of type RAM
   disk_1 of type DISK
   network_1 of type NETWORK
  }
  requiring cpu_0.frequency >= 1770.0
  requiring ram_1.total >= 5516.0
  requiring disk_1.total >= 3842.0
  requiring network_1.throughput >= 27206.0
  providing quality = 55.0
  providing energy = ((0.0*(size^2.0))+(0.03*cpu_0.frequency))
  
 }
 using property quality
 using property energy
}

request request0 for component_0 {
 meta size = 255.0
 requiring quality >= 68.0
}
minimize sum(energy)
solution {
 request0 -> implementation_0i1 {
  compute_resource_0 -> resource0 {
   cpu_0 -> cpu0_0
   ram_1 -> ram0
   disk_1 -> disk0
   network_1 -> network0
  }
  the_component_0c0 --> implementation_0c0i0 {
   compute_resource_0 -> resource1 {
    cpu_0 -> cpu1_0
    ram_1 -> ram1
    disk_1 -> disk1
    network_1 -> network1
   }
   the_component_0c0c0 --> implementation_0c0c0i1 {
    compute_resource_0 -> resource2 {
     cpu_0 -> cpu2_0
     ram_1 -> ram2
     disk_1 -> disk2
     network_1 -> network2
    }
    the_component_0c0c0c0 --> implementation_0c0c0c0i0 {
     compute_resource_0 -> resource3 {
      cpu_0 -> cpu3_0
      ram_1 -> ram3
      disk_1 -> disk3
      network_1 -> network3
     }
     the_component_0c0c0c0c0 --> implementation_0c0c0c0c0i0 {
      compute_resource_0 -> resource4 {
       cpu_0 -> cpu4_0
       ram_1 -> ram4
       disk_1 -> disk4
       network_1 -> network4
      }
     }
     the_component_0c0c0c0c1 --> implementation_0c0c0c0c1i1 {
      compute_resource_0 -> resource5 {
       cpu_0 -> cpu5_0
       ram_1 -> ram5
       disk_1 -> disk5
       network_1 -> network5
      }
     }
    }
    the_component_0c0c0c1 --> implementation_0c0c0c1i0 {
     compute_resource_0 -> resource6 {
      cpu_0 -> cpu6_0
      ram_1 -> ram6
      disk_1 -> disk6
      network_1 -> network6
     }
     the_component_0c0c0c1c0 --> implementation_0c0c0c1c0i1 {
      compute_resource_0 -> resource38 {
       cpu_0 -> cpu38_0
       ram_1 -> ram38
       disk_1 -> disk38
       network_1 -> network38
      }
     }
     the_component_0c0c0c1c1 --> implementation_0c0c0c1c1i0 {
      compute_resource_0 -> resource8 {
       cpu_0 -> cpu8_0
       ram_1 -> ram8
       disk_1 -> disk8
       network_1 -> network8
      }
     }
    }
   }
   the_component_0c0c1 --> implementation_0c0c1i0 {
    compute_resource_0 -> resource9 {
     cpu_0 -> cpu9_0
     ram_1 -> ram9
     disk_1 -> disk9
     network_1 -> network9
    }
    the_component_0c0c1c0 --> implementation_0c0c1c0i0 {
     compute_resource_0 -> resource10 {
      cpu_0 -> cpu10_0
      ram_1 -> ram10
      disk_1 -> disk10
      network_1 -> network10
     }
     the_component_0c0c1c0c0 --> implementation_0c0c1c0c0i0 {
      compute_resource_0 -> resource11 {
       cpu_0 -> cpu11_0
       ram_1 -> ram11
       disk_1 -> disk11
       network_1 -> network11
      }
     }
     the_component_0c0c1c0c1 --> implementation_0c0c1c0c1i0 {
      compute_resource_0 -> resource12 {
       cpu_0 -> cpu12_0
       ram_1 -> ram12
       disk_1 -> disk12
       network_1 -> network12
      }
     }
    }
    the_component_0c0c1c1 --> implementation_0c0c1c1i1 {
     compute_resource_0 -> resource13 {
      cpu_0 -> cpu13_0
      ram_1 -> ram13
      disk_1 -> disk13
      network_1 -> network13
     }
     the_component_0c0c1c1c0 --> implementation_0c0c1c1c0i0 {
      compute_resource_0 -> resource14 {
       cpu_0 -> cpu14_0
       ram_1 -> ram14
       disk_1 -> disk14
       network_1 -> network14
      }
     }
     the_component_0c0c1c1c1 --> implementation_0c0c1c1c1i0 {
      compute_resource_0 -> resource15 {
       cpu_0 -> cpu15_0
       ram_1 -> ram15
       disk_1 -> disk15
       network_1 -> network15
      }
     }
    }
   }
  }
  the_component_0c1 --> implementation_0c1i0 {
   compute_resource_0 -> resource16 {
    cpu_0 -> cpu16_0
    ram_1 -> ram16
    disk_1 -> disk16
    network_1 -> network16
   }
   the_component_0c1c0 --> implementation_0c1c0i1 {
    compute_resource_0 -> resource17 {
     cpu_0 -> cpu17_0
     ram_1 -> ram17
     disk_1 -> disk17
     network_1 -> network17
    }
    the_component_0c1c0c0 --> implementation_0c1c0c0i1 {
     compute_resource_0 -> resource18 {
      cpu_0 -> cpu18_0
      ram_1 -> ram18
      disk_1 -> disk18
      network_1 -> network18
     }
     the_component_0c1c0c0c0 --> implementation_0c1c0c0c0i0 {
      compute_resource_0 -> resource19 {
       cpu_0 -> cpu19_0
       ram_1 -> ram19
       disk_1 -> disk19
       network_1 -> network19
      }
     }
     the_component_0c1c0c0c1 --> implementation_0c1c0c0c1i1 {
      compute_resource_0 -> resource20 {
       cpu_0 -> cpu20_0
       ram_1 -> ram20
       disk_1 -> disk20
       network_1 -> network20
      }
     }
    }
    the_component_0c1c0c1 --> implementation_0c1c0c1i1 {
     compute_resource_0 -> resource21 {
      cpu_0 -> cpu21_0
      ram_1 -> ram21
      disk_1 -> disk21
      network_1 -> network21
     }
     the_component_0c1c0c1c0 --> implementation_0c1c0c1c0i0 {
      compute_resource_0 -> resource22 {
       cpu_0 -> cpu22_0
       ram_1 -> ram22
       disk_1 -> disk22
       network_1 -> network22
      }
     }
     the_component_0c1c0c1c1 --> implementation_0c1c0c1c1i1 {
      compute_resource_0 -> resource23 {
       cpu_0 -> cpu23_0
       ram_1 -> ram23
       disk_1 -> disk23
       network_1 -> network23
      }
     }
    }
   }
   the_component_0c1c1 --> implementation_0c1c1i0 {
    compute_resource_0 -> resource24 {
     cpu_0 -> cpu24_0
     ram_1 -> ram24
     disk_1 -> disk24
     network_1 -> network24
    }
    the_component_0c1c1c0 --> implementation_0c1c1c0i1 {
     compute_resource_0 -> resource25 {
      cpu_0 -> cpu25_0
      ram_1 -> ram25
      disk_1 -> disk25
      network_1 -> network25
     }
     the_component_0c1c1c0c0 --> implementation_0c1c1c0c0i1 {
      compute_resource_0 -> resource26 {
       cpu_0 -> cpu26_0
       ram_1 -> ram26
       disk_1 -> disk26
       network_1 -> network26
      }
     }
     the_component_0c1c1c0c1 --> implementation_0c1c1c0c1i0 {
      compute_resource_0 -> resource27 {
       cpu_0 -> cpu27_0
       ram_1 -> ram27
       disk_1 -> disk27
       network_1 -> network27
      }
     }
    }
    the_component_0c1c1c1 --> implementation_0c1c1c1i0 {
     compute_resource_0 -> resource28 {
      cpu_0 -> cpu28_0
      ram_1 -> ram28
      disk_1 -> disk28
      network_1 -> network28
     }
     the_component_0c1c1c1c0 --> implementation_0c1c1c1c0i1 {
      compute_resource_0 -> resource29 {
       cpu_0 -> cpu29_0
       ram_1 -> ram29
       disk_1 -> disk29
       network_1 -> network29
      }
     }
     the_component_0c1c1c1c1 --> implementation_0c1c1c1c1i1 {
      compute_resource_0 -> resource30 {
       cpu_0 -> cpu30_0
       ram_1 -> ram30
       disk_1 -> disk30
       network_1 -> network30
      }
     }
    }
   }
  }
 }
}
		'''
	}
	
}