class {{brewFormulaName}} < Formula
  desc "{{projectDescription}}"
  homepage "{{projectLinkHomepage}}"
  version "{{projectVersion}}"
  license "{{projectLicense}}"

  {{brewMultiPlatform}}

  def install
    libexec.install Dir["*"]
    os_arch = ""
    if OS.linux? && Hardware::CPU.intel?
      os_arch = "linuxx64"
    elsif OS.mac? && Hardware::CPU.arm?
      os_arch = "darwinarm64"
    elsif OS.mac? && Hardware::CPU.intel?
      os_arch = "darwinx64"
    else
      puts "Unsupported system or architecture!"
    end
    chmod 0755, "#{libexec}/{{distributionName}}-#{os_arch}/{{distributionName}}"
    bin.write_exec_script "#{libexec}/{{distributionName}}-#{os_arch}/{{distributionName}}"
  end
end