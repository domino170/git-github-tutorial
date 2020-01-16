job("First job") {
	description("Moj pierwszy build")
	keepDependencies(false)
	scm {
		git {
			remote {
				github("domino170/pies-transient-temperature", "https")
				credentials("8bff2cbc-33bf-48d4-b171-03878a502a80")
			}
			branch("*/master")
		}
	}
	disabled(false)
	triggers {
		scm("* * * * *") {
			ignorePostCommitHooks(false)
		}
	}
	concurrentBuild(true)
	publishers {
		archiveArtifacts {
			pattern("**/bin/Release/*.exe.*,**/bin/Release/*.exe")
			allowEmpty(false)
			onlyIfSuccessful(true)
			fingerprint(false)
			defaultExcludes(true)
		}
	}
	wrappers {
		preBuildCleanup {
			deleteDirectories(false)
			cleanupParameter()
		}
		timeout {
			absolute(3)
		}
		timestamps()
	}
	configure {
		it / 'properties' / 'com.coravy.hudson.plugins.github.GithubProjectProperty' {
			'projectUrl'('https://github.com/domino170/pies-transient-temperature/')
			displayName()
		}
		it / 'properties' / 'jenkins.model.BuildDiscarderProperty' {
			strategy {
				'daysToKeep'('21')
				'numToKeep'('5')
				'artifactDaysToKeep'('-1')
				'artifactNumToKeep'('-1')
			}
		}
	}
}
