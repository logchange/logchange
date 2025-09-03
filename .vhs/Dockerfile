# Use vhs base image
FROM ghcr.io/charmbracelet/vhs:latest

# Install dependencies for Linuxbrew
RUN apt-get update --allow-releaseinfo-change && apt-get install -y \
    build-essential \
    curl \
    file \
    git \
    sudo \
 && rm -rf /var/lib/apt/lists/*

# Install Homebrew (Linuxbrew)
RUN git clone https://github.com/Homebrew/brew /home/linuxbrew/.linuxbrew/Homebrew \
    && mkdir /home/linuxbrew/.linuxbrew/bin \
    && ln -s /home/linuxbrew/.linuxbrew/Homebrew/bin/brew /home/linuxbrew/.linuxbrew/bin/brew

# Add Homebrew to PATH for all users
ENV PATH="/home/linuxbrew/.linuxbrew/bin:/home/linuxbrew/.linuxbrew/sbin:${PATH}"
ENV HOMEBREW_NO_AUTO_UPDATE=1
ENV HOMEBREW_NO_ENV_HINTS=1


# Keep the original ENTRYPOINT from the base image
